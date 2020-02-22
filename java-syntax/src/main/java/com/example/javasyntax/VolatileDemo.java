package com.example.javasyntax;

import lombok.Data;

public class VolatileDemo {
    public static void main(String[] args){ //这个线程是用来读取flag的值的

        ThreadDemo threadDemo = new ThreadDemo();
        Thread thread = new Thread(threadDemo);
        thread.start();

        int i = 0 ;
        while (true){
//            synchronized (threadDemo) {
                //System.out.println( i++ );
                if (threadDemo.isFlag()) {
                    System.out.println("主线程读取到的flag = " + threadDemo.isFlag());
                    break;
                }
//            }
        }
    }
}

@Data
class ThreadDemo implements Runnable{ //这个线程是用来修改flag的值的
    public volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("ThreadDemo线程修改后的flag = " + isFlag());
    }
}