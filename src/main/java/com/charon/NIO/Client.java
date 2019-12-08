package com.charon.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：NIO客户端
 * @date : 21:43 2019/12/8
 */
public class Client {
    public static void main(String[] args) throws Exception{
        // 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9890));

        // 切换非阻塞模式
        socketChannel.configureBlocking(false);

        // 分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 读取文件发送到服务端
        /*byteBuffer.put(LocalDateTime.now().toString().getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();*/
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String str=scanner.next();
            byteBuffer.put((LocalDateTime.now().toString()+"\n"+str).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        //关闭通道
        socketChannel.close();
    }
}
