package com.example.javasyntax.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class FileNIODemo {

    public static void main(String[] args){ //这个线程是用来读取flag的值的

//        method2();
        method1();

//        FileNIODemo.tranferChannal();
    }

    // 采用FileInputStream读取文件内容的：
    public static void method2() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("/Users/howard/github/Java/README.md"));
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);

            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++)
                    System.out.print((char) buf[i]);

                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 对应的NIO
    private static void method1(){
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("/Users/howard/github/Java/README.md", "rw");
            FileChannel inChannel = aFile.getChannel();

            //创建容量为48byte的buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            //读取数据，放入buffer
            int byteReader = inChannel.read(byteBuffer);

            while (byteReader != -1) {
                System.out.println("Read:" + byteReader);
                //设置buffer切换模式为读模式
                byteBuffer.flip();

                while (byteBuffer.hasRemaining()) {
                    // 每次读取1byte，也就是一个字节
                    System.out.println((char)byteBuffer.get());
                }

                //清空buffer，准备再次写入, position将被设回0，limit设置成capacity，换句话说，Buffer被清空了
                byteBuffer.clear();
                // compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。
                // limit属性依然像clear()方法一样，设置成capacity
                byteBuffer.compact();
                byteReader = inChannel.read(byteBuffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                aFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void tranferChannal() {
        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        try {
            fromFile = new RandomAccessFile("/Users/howard/github/Java/README.md", "rw");
            FileChannel      fromChannel = fromFile.getChannel();

            toFile = new RandomAccessFile("/Users/howard/github/Java/toFile.txt", "rw");
            FileChannel      toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();

            // 将数据从FileChannel传输到其他的channel中
            toChannel.transferFrom(fromChannel,position, count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fromFile.close();
                toFile.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }






}
