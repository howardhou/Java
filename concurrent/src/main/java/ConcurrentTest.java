import java.util.concurrent.*;

public class ConcurrentTest {
    public static void main(String[] args){

        // 使用 Callable ， Future ， FutureTask  可以在异步任务执行完毕后，得到任务执行的结果
        useFuture();

        useFutureTask();

        // 继承Thread类 , 实现多线程
        System.out.println("主线程ID: "+Thread.currentThread().getId());

        MyThread thread = new MyThread();
        thread.start();

        // 实现 Runnable接口 , 实现多线程
        MyRunnable runnable = new MyRunnable();
        Thread thread2 = new Thread(runnable);
        thread2.start();
    }

    // 使用 Callable + Future 获取 异步调用的 执行结果
    private static void useFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        MyTask task = new MyTask();
        Future<Integer> future = executorService.submit(task);

        executorService.shutdown();

        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }

        System.out.println("useFuture: 主线程在执行...");

        try {
            System.out.println("useFuture: Task 运行结果： " + future.get());
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
        catch (ExecutionException ex){
            ex.fillInStackTrace();
        }

        System.out.println("useFuture: 执行完成");
    }

    // 使用 Callable + FutureTask 获取 异步调用的 执行结果
    private static void useFutureTask() {

        MyTask task = new MyTask();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);

        //方法一
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.submit(futureTask);
//        executorService.shutdown();

        // 方法二
        Thread thread = new Thread(futureTask);
        thread.start();


        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }

        System.out.println("useFutureTask: 主线程在执行...");

        try {
            System.out.println("useFutureTask: Task 运行结果： " + futureTask.get());
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
        catch (ExecutionException ex){
            ex.fillInStackTrace();
        }

        System.out.println("useFutureTask: 执行完成");
    }


}
