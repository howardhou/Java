package com.example.jvm;

/**
 *
 * VM Args：-Xss160k （这时候不妨设大些）
 *
 *
 * 没有测试出来
 */

public class JavaVMStackOOM {

       private void dontStop() {

              while (true) {

              }
       }

 

       public void stackLeakByThread() {

              while (true) {

                     Thread thread = new Thread(new Runnable() {

                            @Override

                            public void run() {

                                   dontStop();

                            }

                     });

                     thread.start();

              }

       }

 

       public static void main(String[] args) throws Throwable {

              JavaVMStackOOM oom = new JavaVMStackOOM();

              oom.stackLeakByThread();
       }

}