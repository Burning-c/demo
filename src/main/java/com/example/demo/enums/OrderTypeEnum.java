package com.example.demo.enums;

import com.example.demo.exception.MyException;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author haojie.cui
 * @since 2022/2/28 14:30
 */
@Getter
public enum OrderTypeEnum implements IEnum{
    //
    INSTALL("INSTALL", "补充安装单"),
    REPAIR("REPAIR", "维修单"),
    MAINTAIN("MAINTAIN", "保养单");

    private final String code;
    private final String name;

    OrderTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static OrderTypeEnum getByCode(String code) {
       return Arrays.stream(OrderTypeEnum.values()).filter(e -> e.getCode().equals(code)).findFirst().orElseThrow(() ->  new MyException("枚举【OrderTypeEnun】【" + code + "】不存在"));
    }

    public static void main(String[] args) {
        String type = OrderTypeEnum.getByCode("MAINTAIN").getName();
        System.out.println("type = " + type);
    }

}
