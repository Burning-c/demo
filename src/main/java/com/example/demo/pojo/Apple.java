package com.example.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Apple extends Fruit {
    private String names;

    static {
        System.out.println("Apple static{}");
    }

    {
        System.out.println("Apple {}");
    }

    public Apple() {
        System.out.println("Apple 无参构造");
    }

    public Apple(String name) {
        this.names = name;
    }

    @Override
    public void method(){
        System.out.println(" Apple method");
    }

    public static void main(String[] args) {
        Fruit apple =  new Apple("name:apple");
        System.out.println("apple.getName() = " + apple.getNames());
        apple.method();
    }
}
