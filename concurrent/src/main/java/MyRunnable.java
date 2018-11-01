public class MyRunnable implements Runnable{
    public void run() {
        System.out.println("MyRunnable： 子线程ID："+Thread.currentThread().getId());
    }
}
