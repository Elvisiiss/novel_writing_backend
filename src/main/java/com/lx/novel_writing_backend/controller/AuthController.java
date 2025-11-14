package com.lx.novel_writing_backend.controller;


import com.emsb.equipment_management_system_backend.dto.auth.*;
import com.emsb.equipment_management_system_backend.service.auth.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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


    /**
     * 账号密码登录
     */
    @PostMapping("/login/password")
    public ResponseEntity<AuthResponse> loginWithPassword(@Valid @RequestBody LoginRequest request) {
        log.info("账号密码登录请求，账号：{}", request.getAccount());
        AuthResponse response = authService.loginWithPassword(request);
        return ResponseEntity.ok(response);
    }


}
