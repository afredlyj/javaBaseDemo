package afred.javademo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 2016-03-03 .
 */
public class CyclicBarrierDemo {


    static class SubTask implements Runnable {

        private CyclicBarrier barrier;

        public SubTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " do some work");

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " finish work");

            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void work(int size) {

        CyclicBarrier barrier = new CyclicBarrier(size, new Runnable() {
            @Override
            public void run() {
                System.out.println("finish all work");
            }
        });

        for (int i = 0; i < size; i++) {
            new Thread(new SubTask(barrier)).start();
        }

    }

    public static void main(String[] args) {
        new CyclicBarrierDemo().work(5);

    }

}
