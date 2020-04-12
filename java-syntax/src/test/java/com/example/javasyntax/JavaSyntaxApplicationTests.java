package com.example.javasyntax;

import com.example.javasyntax.multithread.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;


@SpringBootTest
class JavaSyntaxApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testCustomExecutor(){

        // corePoolSize: 线程池大小，一般线程池开始时是没有线程的，只有当任务来了且线程数量小于corePoolSize 才会创建线程
        // maximumPoolSize : 最大线程数
        // keepAliveTime : 在线程数量超过corePoolSize后，多余空闲线程的最大存活时间
        // unit : 参数keepAliveTime的时间单位
        // workQueue : 一个阻塞队列，用来存储等待执行的任务，决定线程池的排队策略
        // threadFactory : 生产线程的工厂类（可选），默认是：Executors.defaultThreadFactory()
        // handler : 拒绝策略，当任务来不及处理时的处理策略（可选）, 有4种策略，默认是 AbortPolicy，表示：丢弃任务并抛出异常
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()); // 表示：由调用线程处理该任务

        for(int i=0;i<30;i++) {
            Task task = new Task();
            executor.submit(task);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+
                    "，队列中等待执行的任务数目："+ executor.getQueue().size()+
                    "，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }

        executor.shutdown();

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}
