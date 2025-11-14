// com.lx.novel_writing_backend.service.impl.AuthServiceImpl.java
package com.lx.novel_writing_backend.service.impl;

import com.lx.novel_writing_backend.dto.LoginRequest;
import com.lx.novel_writing_backend.dto.LoginResponse;
import com.lx.novel_writing_backend.dto.RegisterRequest;
import com.lx.novel_writing_backend.dto.ForgotPasswordRequest;
import com.lx.novel_writing_backend.entity.User;
import com.lx.novel_writing_backend.exception.BusinessException;
import com.lx.novel_writing_backend.mapper.UserMapper;
import com.lx.novel_writing_backend.service.AuthService;
import com.lx.novel_writing_backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        // 查找用户（支持用户名或邮箱登录）
        User user = userMapper.findByUsernameOrEmail(request.getUsername(), request.getUsername());

        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 验证密码
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BusinessException("密码错误");
        }

        // 生成token
        String token = jwtService.generateToken(user);

        // 构建响应
        LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );

        return new LoginResponse(token, userInfo);
    }

    @Override
    public void register(RegisterRequest request) {
        // 检查用户名是否已存在
        if (userMapper.findByUsername(request.getUsername()) != null) {
            throw new BusinessException("用户名已被占用");
        }

        // 检查邮箱是否已存在
        if (userMapper.findByEmail(request.getEmail()) != null) {
            throw new BusinessException("邮箱已被注册");
        }

        // 创建用户对象
        User user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        // 密码加密存储
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        // 保存到数据库
        userMapper.insert(user);
        log.info("用户注册成功: {}", request.getUsername());
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        // 验证用户信息是否匹配
        User user = userMapper.findByNameEmailPhone(
                request.getName(),
                request.getEmail(),
                request.getPhone()
        );

        if (user == null) {
            throw new BusinessException("姓名、邮箱或电话不匹配，无法重置密码");
        }

        // 更新密码（加密存储）
        userMapper.updatePassword(user.getId(), passwordEncoder.encode(request.getNewPassword()));
        log.info("用户密码重置成功: {}", user.getUsername());
    }
}
