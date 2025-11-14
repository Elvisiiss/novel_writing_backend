package com.lx.novel_writing_backend.entity.result;

import com.fasterxml.jackson.annotation.JsonInclude;

// 移除 @Builder 注解
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应状态码 success/Error
     */
    private String code;

    /**
     * 响应数据
     */
    private T data;

    // 成功响应的静态方法（替换为构造方法创建对象）
    public static <T> Result<T> success(String msg) {
        return new Result<>(msg, "success", null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(msg, "success", data);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>("操作成功", "success", data);
    }

    // 失败响应的静态方法（替换为构造方法创建对象）
    public static <T> Result<T> error(String msg) {
        return new Result<>(msg, "Error", null);
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<>(msg, code, null);
    }

    // 保留原有的构造方法和 getter/setter
    public Result() {}

    public Result(String msg, String code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
