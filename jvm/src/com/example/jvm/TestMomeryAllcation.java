package com.example.jvm;

public class TestMomeryAllcation {
    private static final int _1MB = 1024*1024;


    public static void main(String[] args) {
//        testAllocation();

//        testPretenureSizeThreshold();

//        testTenuringThreshold();

//        testTenuringThreshold2();

        testHandlePromotion();
    }

    /**
     * 对象优先在Eden分配
     *
     * VM Args : -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC
     *
     * -Xms20m  : 堆的最小值 20M
     * -Xmx20m  : 堆的最大值 20M ， -Xms 和 -Xmx 设置成一样可以避免堆自动扩展
     * -Xmn10M : 新生代 10M
     * -XX:+UseSerialGC ： Serial + Serial Old
     * -XX:SurvivorRatio=8  :  新生代中Eden和Survivor区的空间比例为8:1
     * -XX:+PrintGCDetails  :  打印收集器日志参数
     *
     */
    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];

        //出现一次Minor GC
        allocation4 = new byte[4*_1MB];
    }

    /**
     * 大对象直接进入老年代
     *
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     * -XX:PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation = new byte[4*_1MB];
    }


    /**
     * 3. 长期存活的对象将进入老年代
     *
     * VM Args : -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold(){
        byte[] allocation1,allocation2,allocation3;
        allocation1 = new byte[_1MB/10];
        allocation2 = new byte[4*_1MB];
        allocation3 = new byte[4*_1MB];
        allocation3 = null;
        allocation3 = new byte[4*_1MB];
    }

    /**
     * 4. 动态对象年龄判定
     *
     * VM Args : -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold2(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1MB/4];
        allocation2 = new byte[_1MB/4];
        allocation3 = new byte[4*_1MB];
        allocation4 = new byte[4*_1MB];
        allocation4 = null;
        allocation4 = new byte[4*_1MB];
    }


    /**
     * 5. 空间分配担保
     *
     * VM Args : -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     * -XX:-HandlePromotionFailure -XX:+PrintGCDetails
     *
     * JDK8 中 -XX:HandlePromotionFailure 参数不再影响VM的担保策略，
     * 规则变为：只要老年代的连续空间大于新生代对象总大小或者历次晋升的平均大小就会进行 Minor GC，否则进行 Full GC
     *
     */
    public static void testHandlePromotion(){
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5,
                allocation6,allocation7;
        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];
//        allocation1 = null;
        allocation4 = new byte[2*_1MB];
        allocation5 = new byte[4*_1MB];
        allocation6 = new byte[4*_1MB];
//        allocation4 = null;
//        allocation5 = null;
//        allocation6 = null;
        allocation7 = new byte[4*_1MB];

    }
}
