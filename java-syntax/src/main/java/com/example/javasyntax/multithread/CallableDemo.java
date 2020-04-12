package com.example.javasyntax.multithread;

import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args){
        Task task = new Task();

// 方法一
//        FutureTask<Integer> result = new FutureTask<>(task);
//        Thread thread = new Thread(result, "有返回值");
//        thread.start();

        // 方法二
        // Callable 一般情况下是配合ExecutorService使用的
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();

        System.out.println("主线程在执行任务");

        try {
            // result.get() 会等待子线程执行完成
            System.out.println("task result : " + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}
