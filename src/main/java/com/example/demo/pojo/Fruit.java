package com.example.demo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Fruit implements Serializable {

    private String names = "name:fruit";

    static {
        System.out.println("fruit static{}");
    }

    {
        System.out.println("fruit {}");
    }

    public Fruit() {
        System.out.println("fruit 无参构造");
    }

    public Fruit(String name) {
        this.names = name;
    }

    public void method(){
        System.out.println(" fruit method");
    }
}
