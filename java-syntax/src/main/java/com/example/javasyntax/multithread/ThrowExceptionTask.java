package com.example.javasyntax.multithread;

import java.util.concurrent.Callable;

public class ThrowExceptionTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println( Thread.currentThread().getName());
        Thread.sleep(3000);
        Integer sum = 100 / 0;
        return sum;
    }
}
