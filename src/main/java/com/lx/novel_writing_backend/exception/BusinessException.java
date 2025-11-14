// BusinessException.java
package com.lx.novel_writing_backend.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private String code;

    public BusinessException(String message) {
        super(message);
        this.code = "400";
    }

    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }
}
