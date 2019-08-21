package com.example.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * VM Args： -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * -XX:PermSize=10M   ： 初始化永久代大小
 * -XX:MaxPermSize=10M  ： 永久代最大容量
 *
 *  没有测试出来
 *
 */

public class JavaMethodAreaOOM {

       public static void main(String[] args) {

              while (true) {

                     Enhancer enhancer = new Enhancer();

                     enhancer.setSuperclass(OOMObject.class);

                     enhancer.setUseCache(false);

                     enhancer.setCallback(new MethodInterceptor() {

                            @Override
                            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

                                   return proxy.invokeSuper(obj, args);

                            }

                     });

                     enhancer.create();

              }

       }

 

       static class OOMObject {

       }

}