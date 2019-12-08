package com.charon.NIO;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：NIO通信 返回客户端消息
 * @date : 21:40 2019/12/8
 */
public class BlockingNIOTest {

    @Test
    public void client() throws Exception{
        // 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel inChannel = FileChannel.open(Paths.get("file.txt"), StandardOpenOption.READ);

        // 分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 读取文件发送到服务端
        while (inChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        socketChannel.shutdownOutput();

        // 接受到服务端反馈
        int len=0;
        while ((len=socketChannel.read(byteBuffer))!=-1){
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(),0,len));
            byteBuffer.clear();
        }

        inChannel.close();
        socketChannel.close();
    }

    @Test
    public void server()throws Exception{
        // 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9898));

        // 获取客户端的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 分配指定大小缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


        // 接受客户端数据保存本地
        FileChannel outChannel = FileChannel.open(Paths.get("NIOFile.txt"),
                StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);
        while (socketChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        // 发送反馈给客户端
        byteBuffer.put("服务端接受数据成功".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);

        // 关闭通道
        outChannel.close();
        socketChannel.close();
        serverSocketChannel.close();
    }
}
