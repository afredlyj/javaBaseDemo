package afred.javademo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Afred on 15/7/19.
 */
public class NioTest {

    public static void main(String[] args) throws IOException {

        byte[] bs = new byte[1024];
//        for (int i = 0; i < bs.length; i++) {
//            bs[i] = (byte)i;
//        }

//        ByteBuffer buffer = ByteBuffer.wrap(bs);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        print(buffer);

        RandomAccessFile file = new RandomAccessFile("/Users/winnie/Documents/git_repo/JavaBaseDemo/access.txt", "r");

        FileChannel channel = file.getChannel();
        while (channel.read(buffer) != -1) {
            System.out.println("before flip");
            print(buffer);
            buffer.flip();
            System.out.println("after flip");

            print(buffer);

            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
//                System.out.println("position : " + buffer.position());
//                System.out.println("array off set : " + buffer.arrayOffset());
            }


            buffer.clear();
        }

        System.out.println("content : " + new String(bs));

        System.out.println("end ... ");

    }

    private static void print(ByteBuffer buffer) {
        System.out.println("capacity : " + buffer.capacity());
        System.out.println("position : " + buffer.position());
        System.out.println("limit : " + buffer.limit());
    }

}
