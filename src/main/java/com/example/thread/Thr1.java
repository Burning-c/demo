package com.example.thread;

public class Thr1 {
    public static void main(String[] args) throws InterruptedException {
      //  System.out.println(Thread.currentThread().getName());
        MyThread t1 = new MyThread();
        Thread th1 = new Thread(t1, "线程1");
        Thread th2 = new Thread(t1, "线程2");
       // th1.setPriority(Thread.MIN_PRIORITY);
       // th2.setPriority(Thread.MAX_PRIORITY);
        th1.start();
        th2.start();
       // th1.join();;
    }

    public static class MyThread extends Thread {
        private int total = 10;

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (this) {
                    if (total > 0) {
                        try {
                            Thread.sleep(100);
                            System.out.println(Thread.currentThread().getName() + "卖票---->" + (this.total--));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
