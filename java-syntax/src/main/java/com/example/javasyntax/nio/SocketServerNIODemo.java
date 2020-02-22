package com.example.javasyntax.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SocketServerNIODemo {


    private static final int BUF_SIZE=1024;
    private static final int PORT = 8090;
    private static final int TIMEOUT = 3000;
    public static void main(String[] args)
    {
        selector();
    }

    // 监听新进来的连接：
    public static void handleAccept(SelectionKey key) throws IOException {
        // 从SelectionKey访问Channel
        ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();

        SocketChannel sc = ssChannel.accept();

        sc.configureBlocking(false);

        //可以在用register()方法向Selector注册Channel的时候附加对象 - 这个对象就是 buffer
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }

    // 监听 读
    public static void handleRead(SelectionKey key) throws IOException{
        SocketChannel sc = (SocketChannel)key.channel();

        // 可以附加 与通道一起使用的Buffer
        ByteBuffer buf = (ByteBuffer)key.attachment();

        long bytesRead = sc.read(buf);
        while(bytesRead>0){
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if(bytesRead == -1){
            sc.close();
        }
    }

    // 监听写
    public static void handleWrite(SelectionKey key) throws IOException{
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while(buf.hasRemaining()){
            sc.write(buf);
        }
        buf.compact();
    }

    public static void selector() {
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try{
            // Selector的创建
            selector = Selector.open();

            // 打开ServerSocketChannel：
            ssc= ServerSocketChannel.open();

            //绑定端口
            ssc.socket().bind(new InetSocketAddress(PORT));
            // 与Selector一起使用时，Channel必须处于非阻塞模式下
            ssc.configureBlocking(false);
            // 必须将Channel注册到Selector上 ，
            // 第二个参数。这是一个“interest集合”（interest集合是你所选择的感兴趣的事件集合），接收就绪
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            while(true){
                // select 方法 返回你所感兴趣的事件（如连接、接受、读或写）已经准备就绪的那些通道
                // TIMEOUT 表示阻塞的时间
                if(selector.select(TIMEOUT) == 0){
                    System.out.println("==");
                    continue;
                }

                // Set selectedKeys = selector.selectedKeys(); 访问“已选择键集（selected key set）”中的就绪通道

                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while(iter.hasNext()){
                    SelectionKey key = iter.next();
                    if(key.isAcceptable()){
                        handleAccept(key);
                    }
                    if(key.isReadable()){
                        handleRead(key);
                    }
                    if(key.isWritable() && key.isValid()){
                        handleWrite(key);
                    }
                    if(key.isConnectable()){
                        System.out.println("isConnectable = true");
                    }

                    // Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。下次该通道变成就绪时，Selector会再次将其放入已选择键集中
                    iter.remove();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(selector!=null){
                    selector.close();
                }
                if(ssc!=null){
                    // 关闭ServerSocketChannel：
                    ssc.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}
