package com.charon.NIO;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：缓冲区数据获取
 * @date : 18:28 2019/12/8
 */
public class BufferTest {

    @Test
    public void test1(){

        String string="charon";

        // 分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("-----------------allocate--------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        // 将数据存入缓冲区
        byteBuffer.put(string.getBytes());
        System.out.println("-----------------put()--------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        // 切换读取数据模式
        byteBuffer.flip();
        System.out.println("-----------------flip()--------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        // 读取缓冲区数据
        byte[] bytes=new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println("-----------------get()--------------");
        System.out.println(new String(bytes,0,bytes.length));
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        // 可重复读数组
        byteBuffer.rewind();
        System.out.println("-----------------rewind()--------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        // 清空
        byteBuffer.clear();
        System.out.println("-----------------clear()--------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }

    @Test
    public void test2(){
        String string="charon";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(string.getBytes());
        byteBuffer.flip();
        byte[] bytes=new byte[byteBuffer.limit()];
        byteBuffer.get(bytes,0,2);
        System.out.println(new String(bytes,0,2));
        System.out.println(byteBuffer.position());
        // 标记
        byteBuffer.mark();
        byteBuffer.get(bytes,2,4);
        System.out.println(new String(bytes,2,4));
        System.out.println(byteBuffer.position());
        // 恢复到mark位置
        byteBuffer.reset();
        System.out.println(byteBuffer.position());

        // 判断缓冲区是否数据
        if(byteBuffer.hasRemaining()){
            // 获取缓冲区中可操作的数据
            System.out.println(byteBuffer.remaining());
        }
    }


    @Test
    public void  test(){
        // 分配非直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        System.out.println(byteBuffer.isDirect());

    }
}
