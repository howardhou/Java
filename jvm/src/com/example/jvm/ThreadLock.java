package com.example.jvm;

/**
 * 线程死锁等待演示
 */
public class ThreadLock implements Runnable {
    int a, b;

    public ThreadLock(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (Integer.valueOf(a)) {
            synchronized (Integer.valueOf(b)) {
                System.out.println(a + b);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new ThreadLock(1, 2)).start();
            new Thread(new ThreadLock(2, 1)).start();
        }

        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}