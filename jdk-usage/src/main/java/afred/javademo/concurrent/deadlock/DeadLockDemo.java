package afred.javademo.concurrent.deadlock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by winnie on 2016-03-03 .
 */

/**
 * 模拟死锁
 * 死锁产生的条件：
 * 互斥、请求和保持、不剥夺、环路等待
 */
public class DeadLockDemo {

    private static CountDownLatch latch = new CountDownLatch(2);

    public static void m1() throws InterruptedException {

        latch.countDown();
        latch.await();

        synchronized (String.class) {
            System.out.println("m1 : acquired lock on String.class object, try to acquire lock on Integer.class object");

            synchronized (Integer.class) {
                System.out.println("m1 : acquired lock on Integer.class object");
            }
        }
    }

    public static void m2() throws InterruptedException {

        latch.countDown();
        latch.await();

        synchronized (Integer.class) {
            System.out.println("m2 : acquired lock on Integer.class object, try to acquire lock on String.class object");

            synchronized (String.class) {
                System.out.println("m2 : acquired lock on String.class object");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    m1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    m2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
