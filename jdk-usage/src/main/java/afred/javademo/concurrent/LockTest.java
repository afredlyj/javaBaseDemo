package afred.javademo.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by afred on 17/1/16.
 */
public class LockTest {

    @Test
    public void test() throws InterruptedException {

        final ReentrantLock lock = new ReentrantLock();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start lock");
                lock.lock();

                System.out.println("locked");
                try {

                    while (true) {

                    }

                } finally {

                    System.out.println("un lock");
                    lock.unlock();

                }
            }
        });

        thread.start();

        System.out.println("interrupt");
        thread.interrupt();


        System.out.println("end");
        TimeUnit.SECONDS.sleep(10);

    }

}
