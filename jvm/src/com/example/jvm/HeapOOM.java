package com.example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * JAVA 堆溢出
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * -Xms20m  : 堆的最小值 20M
 * -Xmx20m  : 堆的最大值 20M， -Xms 和 -Xmx 设置成一样可以避免堆自动扩展
 * -XX:+HeapDumpOnOutOfMemoryError ： 让 VM 在出现内存溢出异常时 dump 出当前的内存堆存储快照，以便进行分析
 *
 *  触发 OutOfMemoryError 异常
 */

public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<OOMObject>();


        while (true) {

            list.add(new OOMObject());

        }
    }
}
