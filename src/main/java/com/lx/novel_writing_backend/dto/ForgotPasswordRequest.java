// ForgotPasswordRequest.java
package com.lx.novel_writing_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ForgotPasswordRequest {
    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    public ForgotPasswordRequest() {}

    public ForgotPasswordRequest(String name, String email, String phone, String newPassword) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.newPassword = newPassword;
    }

    public @NotBlank(message = "姓名不能为空") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "姓名不能为空") String name) {
        this.name = name;
    }

    public @NotBlank(message = "邮箱不能为空") @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "邮箱不能为空") @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确") String email) {
        this.email = email;
    }

    public @NotBlank(message = "电话不能为空") @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "电话不能为空") @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确") String phone) {
        this.phone = phone;
    }

    public @NotBlank(message = "新密码不能为空") String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(@NotBlank(message = "新密码不能为空") String newPassword) {
        this.newPassword = newPassword;
    }
}
