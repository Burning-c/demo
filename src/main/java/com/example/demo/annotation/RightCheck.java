package com.example.demo.annotation;

import java.lang.annotation.*;
/**
 * @author haojie.cui
 * @since 2022/3/7 16:37
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RightCheck {

    String[] value() default {};
}
