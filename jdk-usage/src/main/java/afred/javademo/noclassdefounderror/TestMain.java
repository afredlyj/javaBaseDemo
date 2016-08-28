package afred.javademo.noclassdefounderror;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Afred on 15/4/25.
 */
public class TestMain {
    public static void main(String[] args) {

        System.out.println("start");

        final CountDownLatch latch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    new SsoManager();
                } catch (Throwable e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        }).start();

        System.out.println("await runnable task ... ");

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("after await runnable task ... ");

        new SsoManager().test();

        System.out.printf("end");
    }
}
