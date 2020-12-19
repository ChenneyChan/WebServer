package com.chenney.webProject.bean;

import lombok.Data;

@Data
public class ResultBody {
    private int code;
    private String message;
    private Object data;

    ResultBody(int code, String msg, Object object) {
        this.code = code;
        this.message = msg;
        this.data = object;
    }

    public static ResultBody ok(Object object) {
        return new ResultBody(0, "success", object);
    }

    public static ResultBody error(String msg) {
        return new ResultBody(30000, msg, null);
    }
}
