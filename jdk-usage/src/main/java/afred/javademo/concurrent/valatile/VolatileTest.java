package afred.javademo.concurrent.valatile;

import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/8/21.
 */
public class VolatileTest {

    public static volatile int race = 0;

    public static void increase() {
        race++;

    }


    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        increase();

                    }
                }
            });

            threads[i].start();
        }

        TimeUnit.SECONDS.sleep(60);

        System.out.println(race);
    }

}
