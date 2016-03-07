package afred.javademo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by winnie on 2016-03-07 .
 */
public class SemaphoreDemo {

    private static final int COUNT = 10;
    private static final int N_THREADS = 30;

    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(10);
        ExecutorService service = Executors.newFixedThreadPool(N_THREADS);

        for (int i = 0; i < N_THREADS; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " start to work");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        service.shutdown();

    }

}
