package com.example.test;

import cn.hutool.core.date.*;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author haojie.cui
 * @since 2021/12/3 15:13
 */
public class LocalTest {
    public static void main(String[] args) {
       double mul = NumberUtil.mul(2,4);
        System.out.println("amount = " + mul);

        double div = NumberUtil.div(4,2);
        System.out.println("amount = " + div);

        double add = NumberUtil.add(2,4);
        System.out.println("amount = " + add);

        double sub = NumberUtil.sub(5,2);
        System.out.println("amount = " + sub);

        BigDecimal a = new BigDecimal(-4.08);
        BigDecimal b = new BigDecimal(-2.02);
        System.out.println("num = " + a.subtract(b));
        System.out.println("sub = " + NumberUtil.sub(a,b));
        System.out.println(NumberUtil.mul(a, new Integer(10)));
        //double r = Math.Math.random()*10;
       // System.out.println("random == " + r);
        String format = StrUtil.format("bearer {}", "123");
        System.out.println("format = " + format);
     DateBetween dateBetween = DateBetween.create(DateUtil.parse("2021-12-22 15:12:00", "yyyy-MM-dd HH:mm:ss"),new Date());
     System.out.println("dateBetween = " + dateBetween);


    }
}
