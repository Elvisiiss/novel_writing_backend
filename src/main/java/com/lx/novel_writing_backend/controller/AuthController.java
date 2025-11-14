// AuthController.java
package com.lx.novel_writing_backend.controller;

import com.lx.novel_writing_backend.dto.ApiResponse;
import com.lx.novel_writing_backend.dto.LoginRequest;
import com.lx.novel_writing_backend.dto.LoginResponse;
import com.lx.novel_writing_backend.dto.RegisterRequest;
import com.lx.novel_writing_backend.dto.ForgotPasswordRequest;
import com.lx.novel_writing_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 认证控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    // 已有的登录接口...
    @PostMapping("/login/password")
    public ApiResponse<LoginResponse> loginWithPassword(@Valid @RequestBody LoginRequest request) {
        log.info("用户登录: {}", request.getUsername());
        LoginResponse response = authService.login(request);
        return ApiResponse.success("登录成功", response);
    }

    // 新增：注册接口
    @PostMapping("/register")
    public ApiResponse<String> register(@Valid @RequestBody RegisterRequest request) {
        log.info("用户注册: {}", request.getUsername());
        authService.register(request);
        return ApiResponse.success("注册成功");
    }

    // 新增：忘记密码接口
    @PostMapping("/forgot-password")
    public ApiResponse<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        log.info("用户密码重置请求: {}", request.getEmail());
        authService.forgotPassword(request);
        return ApiResponse.success("密码重置成功");
    }

    // 已有的退出登录接口...
    @PostMapping("/logout")
    public ApiResponse<String> logout() {
        // TODO: 实现退出逻辑（如将token加入黑名单）
        return ApiResponse.success("退出成功");
    }
}
