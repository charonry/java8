package com.charon.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：NIO服务端
 * @date : 21:43 2019/12/8
 */
public class Server {
    public static void main(String[] args) throws Exception{
        // 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 切换非阻塞模式
        serverSocketChannel.configureBlocking(false);

        // 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9890));

        // 获取选择器
        Selector selector = Selector.open();

        // 将通道注册到选择器并指定监听接受事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 轮询获取选择器上准备就绪的事件
        while (selector.select()>0){
            // 获取当前选择器中所有注册的监听事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()){
                // 获取准备就绪事件
                SelectionKey selectionKey = iterator.next();

                // 判断具体是什么事件准备就绪
                if(selectionKey.isAcceptable()){
                    // 如接受就绪获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    // 切换非阻塞模式
                    socketChannel.configureBlocking(false);

                    // 将该通道注册到选择器上
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    // 获取当前选择器上读就绪状态的通道
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                    // 读取数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len=0;
                    while ((len=socketChannel.read(byteBuffer))>0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,len));
                        byteBuffer.clear();
                    }
                }

                // 取消选择键 selectionKey
                iterator.remove();
            }
        }

    }
}
