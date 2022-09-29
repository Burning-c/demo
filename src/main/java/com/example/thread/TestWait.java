package com.example.thread;

import java.util.List;
import java.util.Vector;

public class TestWait {
    public static void main(String[] args) {
        Object food = new Object();
        Vector<Integer> data = new Vector<>(12);

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (food){
                    System.out.println(Thread.currentThread().getName() + "买饭。。。");
                    try {
                        food.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "吃饭。。。");
                }
            }
        }, "顾客").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (food){
                    System.out.println(Thread.currentThread().getName() + "做饭。。。");
                    try {
                      //  food.notify();//只提示唤醒顾客线程，不会释放当前线程占用的锁
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + "做完饭。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    food.notify();
                }
            }
        }, "厨师").start();

    }
}
