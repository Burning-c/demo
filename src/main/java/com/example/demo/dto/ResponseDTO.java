package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author haojie.cui
 * @since 2022/3/10 13:35
 */
@Data
public class ResponseDTO<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;
    private Long timestamp;

    public ResponseDTO() {
    }

    public ResponseDTO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseDTO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO(200, "成功", data);
    }

    public static <T> ResponseDTO<T> failure() {
        return new ResponseDTO(400, "失败");
    }

    public static <T> ResponseDTO<T> success() {
        return new ResponseDTO(200, "成功");
    }

}
