public class MyThread extends Thread {

    private static int num = 0;

    public MyThread(){
        num++;
    }

    @Override
    public void run() {
        System.out.println("MyThread： 主动创建的第"+num+"个线程， " +" 子线程ID: "+Thread.currentThread().getId());
    }
}
