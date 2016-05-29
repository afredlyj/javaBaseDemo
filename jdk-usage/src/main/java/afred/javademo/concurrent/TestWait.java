package afred.javademo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 2015-05-11 .
 */
public class TestWait {

    public static void main(String[] args) {

        final String lock = "helloworld";

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("run1 ... ");

                    try {

                        TimeUnit.SECONDS.sleep(10);

                        lock.wait();        // release lock
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    System.out.println("run2 ... ");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        System.out.printf("finish ... ");
    }

}
