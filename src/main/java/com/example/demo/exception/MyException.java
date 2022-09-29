package com.example.demo.exception;

/**
 * @author haojie.cui
 * @since 2022/2/24 17:24
 */
public class MyException extends  RuntimeException{
    private String msg;

    public MyException(String msg){
        super(msg);
    }
}
