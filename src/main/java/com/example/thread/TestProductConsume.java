package com.example.thread;

import java.util.Vector;

public class TestProductConsume {
    public static void main(String[] args) {
        // 使用wait()和notify()实现生产者消费者模式

        Object food = new Object();
        Vector<Integer> data = new Vector<>(12);

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (data) {
                    try {
                        if(data.size() == 0){
                            System.out.println(Thread.currentThread().getName() + "，生产中。");
                            data.add(1);
                        }
                        if(data.size() > 0){
                            System.out.println(Thread.currentThread().getName() + "：库存充足，通知消费。");
                            data.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "生产者").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (data) {
                    if(data.size() > 0){
                        System.out.println(Thread.currentThread().getName() + "，消费。");
                        data.remove(data.size() - 1);
                    }
                    if(data.size() == 0){
                        System.out.println(Thread.currentThread().getName() + "：库存不足，通知生产。");
                        data.notify();
                    }
                }
            }
        }, "消费者").start();


    }


}
