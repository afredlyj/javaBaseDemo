package afred.javademo.interrupted;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Afred on 15/7/8.
 */
public class Interrupt {

    public static void main(String[] args) {

        System.out.println(" main " + Thread.currentThread().getName() + Thread.currentThread().isInterrupted());

        final CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("before sleep " + Thread.currentThread().getName() + Thread.currentThread().isInterrupted());
//                    TimeUnit.SECONDS.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("after sleep " + Thread.currentThread().getName() + Thread.currentThread().isInterrupted());

                } finally {
                    latch.countDown();
                }
            }
        });

        thread.start();
        /**
         *
         * 将thread 线程的中断标识设为true00u
         */
        thread.interrupt();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
