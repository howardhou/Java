package com.example.jvm;

/**
 *
 * VM Args：-Xss160k
 *
 * -Xss160k : 设置 VM 栈 容量 160K
 * 触发 StackOverflowError 异常
 *
 */

public class JavaVMStackSOF {

       private int stackLength = 1;

       public void stackLeak() {

              stackLength++;

              stackLeak();
       }

       public static void main(String[] args) throws Throwable {

              JavaVMStackSOF oom = new JavaVMStackSOF();

              try {
                     oom.stackLeak();

              } catch (Throwable e) {
                     System.out.println("stack length:" + oom.stackLength);
                     throw e;
              }

       }

}