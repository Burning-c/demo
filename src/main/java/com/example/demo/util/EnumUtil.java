package com.example.demo.util;

import cn.hutool.core.lang.Assert;
import com.example.demo.dto.EnumDto;
import com.example.demo.enums.IEnum;
import com.example.demo.enums.OrderTypeEnum;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haojie.cui
 * @since 2022/2/28 15:10
 */

public class EnumUtil {
   static String  name = "aa";

    public static <T extends Enum<T>> String getByEnum(Class<T> enumClass, String code) {
        if(enumClass != null && code != null){
            Enum[] enums = (Enum[]) enumClass.getEnumConstants();
            if(enums != null){
                for (Enum anEnum : enums) {
                    if (((IEnum) anEnum).getCode().equals(code)) {
                        return ((IEnum) anEnum).getName();
                    }
                }
            }
        }
        return "";
    }
    public static <T extends Enum<T>> List<EnumDto> getByEnum(Class<T> enumClass) {
        List<EnumDto> dos = new ArrayList<>();;
        Enum<T>[] enums = enumClass.getEnumConstants();
        for (Enum<T> anEnum : enums) {
            String code = ((IEnum)anEnum).getCode().toString();
            String name = ((IEnum)anEnum).getName();
            dos.add(new EnumDto(code,name));
        }
        return dos;
    }
    public static void main(String[] args) {
        String name = EnumUtil.getByEnum(OrderTypeEnum.class, "REPAIR");
        System.out.println("name = " + name);
        System.out.println("------------------------------");
       // EnumUtil.getByEnum(OrderTypeEnum.class);
    }
}
