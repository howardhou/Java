package com.example.javasyntax;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicDemo {
    public static void main(String[] args){
        AtomicThread atomicThread = new AtomicThread();
        for (int x = 0;x < 10; x++){
            new Thread(atomicThread).start();
        }
    }
}

class AtomicThread implements Runnable{
    private AtomicInteger i = new AtomicInteger();


//    private int i = 0;
    public int getI(){
//        return i++;
        return i.getAndIncrement();
    }
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getI());
    }
}
