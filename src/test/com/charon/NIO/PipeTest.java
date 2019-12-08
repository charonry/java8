package com.charon.NIO;


import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：管道
 * @date : 23:24 2019/12/8
 */
public class PipeTest {

    @Test
    public void test() throws  Exception{
        // 获取管道
        Pipe pipe = Pipe.open();

        // 数据写入管道
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        Pipe.SinkChannel sinkChannel = pipe.sink();
        byteBuffer.put("通过管道发送数据".getBytes());
        byteBuffer.flip();
        sinkChannel.write(byteBuffer);

        // 读取缓冲区数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        byteBuffer.flip();
        int len=sourceChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(),0,len));

        sourceChannel.close();
        sinkChannel.close();
    }

}
