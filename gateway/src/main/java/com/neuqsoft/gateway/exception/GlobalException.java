package com.neuqsoft.gateway.exception;

import lombok.Data;

/**
 * @author sunjiarui
 * @Date 2020/9/22
 */
@Data
public class GlobalException extends RuntimeException {
    private String code;
    private String buried;
    private long timestamp;
    private String path;


    public GlobalException(String code, String msg) {
        super(msg);
        this.code = code;
        this.timestamp = System.currentTimeMillis();
    }

    public GlobalException(String code, String msg, String buried) {
        this(code, msg);
        this.buried = buried;
    }

    public GlobalException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.timestamp = System.currentTimeMillis();
    }

    public GlobalException(String code, String message, Throwable throwable, String buried) {
        this(code, message, throwable);
        this.buried = buried;
    }
}
