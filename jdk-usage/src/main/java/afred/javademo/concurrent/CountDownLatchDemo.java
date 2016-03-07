package afred.javademo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 2016-03-03 .
 */
public class CountDownLatchDemo {

    class SubTask implements Runnable {

        private CountDownLatch latch;

        public SubTask(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " do some work");

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            latch.countDown();

            System.out.println(Thread.currentThread().getName() + "finish work");
        }
    }


    public void work(int size) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(new SubTask(latch)).start();
        }

        latch.await();

        System.out.println("all task finish");

    }

    public static void main(String[] args) throws InterruptedException {
        new CountDownLatchDemo().work(5);

    }


}
