package com.charon.NIO;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：文件复制
 * @date : 19:44 2019/12/8
 */
public class ChannelTest {

    // 利用通道完成文件的复制
    @Test
    public void test1() throws Exception{
        LocalDateTime start = LocalDateTime.now();

        FileInputStream fis=new FileInputStream("file.txt");
        FileOutputStream fos=new FileOutputStream("copyFile.txt");
        
        // 获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        // 分配指点大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 将通道中数据放入缓存区
        while (inChannel.read(byteBuffer)!=-1){
            // 切换读取数据模式
            byteBuffer.flip();
            // 将数据写入通道中
            outChannel.write(byteBuffer);
            // 清空缓存区
            byteBuffer.clear();
        }
        outChannel.close();
        inChannel.close();
        fos.close();
        fis.close();

        LocalDateTime end = LocalDateTime.now();
        System.out.println("耗费时间"+ Duration.between(start,end).getSeconds());
    }

    // 使用直接缓冲区完成文件的复制(内存映射文件)
    @Test
    public void test2() throws Exception{
        LocalDateTime start = LocalDateTime.now();
        FileChannel inChannel = FileChannel.open(Paths.get("file.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("copyFile2.txt"),
                StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        // 内存映射文件
        MappedByteBuffer inMappedBuf= inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接堆缓存区进行数据的读写操作
        byte[] bytes=new byte[inMappedBuf.limit()];
        inMappedBuf.get(bytes);
        outMappedBuf.put(bytes);

        outChannel.close();
        inChannel.close();

        LocalDateTime end = LocalDateTime.now();
        System.out.println("耗费时间"+ Duration.between(start,end).getSeconds());

    }

    @Test
    public void test3() throws Exception{
        FileChannel inChannel = FileChannel.open(Paths.get("file.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("copyFile3.txt"),
                StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);
        //inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel,0,inChannel.size());
        inChannel.close();
        outChannel.close();
    }

    // 分散与聚集
    @Test
    public void test4() throws Exception {
        RandomAccessFile file=new RandomAccessFile("file.txt","rw");

        // 获取通道
        FileChannel channel = file.getChannel();

        // 分配缓冲区
        ByteBuffer byteBuffer1=ByteBuffer.allocate(100);
        ByteBuffer byteBuffer2=ByteBuffer.allocate(1024);

        // 分散读取
        ByteBuffer[] byteBuffers={byteBuffer1,byteBuffer2};
        channel.read(byteBuffers);

        for(ByteBuffer byteBuffer:byteBuffers){
            byteBuffer.flip();
        }
        System.out.println(new String(byteBuffers[0].array(),0,byteBuffers[0].limit()));
        System.out.println("-----------------------------------------------------------------");
        System.out.println(new String(byteBuffers[1].array(),0,byteBuffers[1].limit()));

        // 聚集写入
        RandomAccessFile file2=new RandomAccessFile("file2.txt","rw");
        FileChannel channel1 = file2.getChannel();
        channel1.write(byteBuffers);
    }

    // 字符集：Charset
    @Test
    public void test5(){
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();
        for(Map.Entry entry:set){
            System.out.println(entry.getKey()+ "="+entry.getValue());
        }
    }

    @Test
    public void test6() throws Exception{
        Charset charset = Charset.forName("GBK");
        // 获取编码器
        CharsetEncoder charsetEncoder = charset.newEncoder();
        // 获取解码器
        CharsetDecoder charsetDecoder = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("喀戎");
        charBuffer.flip();

        // 编码
        ByteBuffer encode = charsetEncoder.encode(charBuffer);
        for(int i=0;i<4;i++){
            System.out.println(encode.get());
        }

        // 解码
        encode.flip();
        CharBuffer decode = charsetDecoder.decode(encode);
        System.out.println(decode.toString());

        System.out.println("-------------------------------------");

        encode.flip();
        Charset charset1 = Charset.forName("UTF-8");
        CharBuffer buffer = charset1.decode(encode);
        System.out.println(buffer.toString());
    }
}
