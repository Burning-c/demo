package com.example.demo.aspect;

import com.example.demo.annotation.RightCheck;
import com.example.demo.exception.MyException;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author haojie.cui
 * @since 2022/3/7 16:37
 */
@Aspect
@Component
public class RightAspect {

    @Pointcut("@annotation(rightCheck)")
    public void rightCut(RightCheck rightCheck) {
    }

    @Before(value = "rightCut(rightCheck)", argNames = "rightCheck")
    public void rightBefore(RightCheck rightCheck) {
        boolean bo = Arrays.stream(rightCheck.value()).anyMatch(this::hasInclude);
        if (!bo) {
            throw new MyException("您的权限不足");
        }
    }

    public boolean hasInclude(String right) {
        List<String> rightStr = new ArrayList<>(Arrays.asList("admin", "user", "menu"));
        return rightStr.contains(right);
    }


}
