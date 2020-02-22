package com.example.javasyntax.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketClientDemo {
    public static void main(String[] args){ //这个线程是用来读取flag的值的

        client();
    }

    // TCP应用案例，客户端采用NIO实现，而服务端依旧使用BIO实
    public static void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            // 打开SocketChannel：
            socketChannel = SocketChannel.open();
            // 非阻塞式的信道
            socketChannel.configureBlocking(false);

            socketChannel.connect(new InetSocketAddress("localhost", 8090));

            if (socketChannel.finishConnect()) {
                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm " + i++ + "-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    // 关闭 SocketChannel：
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
