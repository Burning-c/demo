package com.example.thread;

public class ThreadSort {

     static class Thr1 implements Runnable{

        @Override
        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thr1 t1 = new Thr1();
        Thread th1 = new Thread(t1, "线程1");
        Thread th2 = new Thread(t1, "线程2");
        Thread th3 = new Thread(t1, "线程3");
        th1.start();
        th1.interrupt();

        System.out.println("th1:" + th1.isInterrupted());
        System.out.println("th1:" + th1.isInterrupted());
        System.out.println("th1:" + Thread.interrupted());
        System.out.println("th1:" + th1.isInterrupted());
        System.out.println("isAlive:" + th1.isAlive());

    }
}
