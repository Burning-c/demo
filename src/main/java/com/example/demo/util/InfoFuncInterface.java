package com.example.demo.util;

/**
 * @author haojie.cui
 * @since 2021/12/28 15:15
 */
@FunctionalInterface
public interface InfoFuncInterface {

   String get(String value);
   default String getInfo() {
       return "";
   }
}
