package com.charon.NIO;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author : charon
 * @version : 1.0
 * @Describe ：
 * @date : 23:06 2019/12/8
 */
public class DatagramChannelTest {

    @Test
    public void send() throws Exception{
        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.configureBlocking(false);

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        Scanner scanner=new Scanner(System.in);

        while (scanner.hasNext()){
            String str=scanner.next();
            byteBuffer.put((new Date().toString()+"\n"+str).getBytes());
            byteBuffer.flip();
            datagramChannel.send(byteBuffer,new InetSocketAddress("127.0.0.1",9999));
            byteBuffer.clear();
        }

        datagramChannel.close();
    }

    @Test
    public void receive() throws Exception{
        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.configureBlocking(false);

        datagramChannel.bind(new InetSocketAddress(9999));

        Selector selector = Selector.open();

        datagramChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select()>0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isReadable()){
                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    datagramChannel.receive(byteBuffer);
                    byteBuffer.flip();
                    System.out.println(new String(byteBuffer.array(),0,byteBuffer.limit()));
                    byteBuffer.clear();
                }
            }
            iterator.remove();
        }
    }

}
