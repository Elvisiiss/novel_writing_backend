package com.lx.novel_writing_backend.entity.result;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<Object> {

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应状态码 success/Error
     */
    private String code;

    public Result() {}

    public Result(String msg, String code) {
        this.msg = msg;
        this.code = code;
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
}
