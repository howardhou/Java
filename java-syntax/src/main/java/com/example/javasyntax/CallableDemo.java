package com.example.javasyntax;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class CallThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Integer i=0;
        for (;i<100 ; i++){

            System.out.println( Thread.currentThread().getName() + " i: "+ i);
        }

        return i;
    }
}

class CallThread2 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}

public class CallableDemo {

    public static void main(String[] args){
        CallThread2 callThread = new CallThread2();

        FutureTask<Integer> task = new FutureTask<>(callThread);

        Thread thread = new Thread(task, "有返回值");
        thread.start();

        try {
            System.out.println("task result : " + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
