package com.example.demo.proxy;

import com.example.demo.service.IUserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Administrator
 */
public class UserInvokeHandle implements InvocationHandler {

    private IUserService iUserService;

    public UserInvokeHandle(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start===");
        Object result = method.invoke(iUserService, args);
        System.out.println("end====");
        return result;
    }
}
