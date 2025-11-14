// com.lx.novel_writing_backend.service.AuthService.java
package com.lx.novel_writing_backend.service;

import com.lx.novel_writing_backend.dto.LoginRequest;
import com.lx.novel_writing_backend.dto.LoginResponse;
import com.lx.novel_writing_backend.dto.RegisterRequest;
import com.lx.novel_writing_backend.dto.ForgotPasswordRequest;

public interface AuthService {

    /**
     * 密码登录
     * @param request 登录请求参数
     * @return 登录响应（包含token和用户信息）
     */
    LoginResponse login(LoginRequest request);

    /**
     * 用户注册
     * @param request 注册请求参数
     */
    void register(RegisterRequest request);

    /**
     * 忘记密码（密码重置）
     * @param request 密码重置请求参数
     */
    void forgotPassword(ForgotPasswordRequest request);
}
