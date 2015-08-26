package afred.demo.load;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Afred on 15/8/10.
 */
public class LoadTest {

    public static void main(String[] args) throws InterruptedException {
        int count = 1000;
        for (int i = 0; i < count; i++) {
            final Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            RandomAccessFile file = new RandomAccessFile("/tmp/test.bin", "rw");
                            file.seek(1024 * 1024 * 500);
                            file.write(1);
                            file.close();

                        }
                    } catch (IOException e) {

                    }
                }
            });
            t.start();
        }
        Thread.currentThread().join();
    }
}
