package com.example.demo.service.impl;

import com.example.demo.service.IUserService;
import org.springframework.stereotype.Service;

@Service("IUserService")
public class UserServiceImpl implements IUserService {

    @Override
    public String getByToken(String token) {
        System.out.println("获取token");
        return "token";
    }
}
