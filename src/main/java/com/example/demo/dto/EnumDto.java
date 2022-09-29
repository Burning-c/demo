package com.example.demo.dto;/**
 * @author itoutsource.cz28
 * @date 2021/9/8
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @author haojie.cui
 * @since 2022/2/28 16:53
 */
@Data
public class EnumDto implements Serializable {
    private Object code;
    private String name;
    public EnumDto(Object code, String name){
        this.code=code;
        this.name=name;
    }
    public EnumDto(){}

}