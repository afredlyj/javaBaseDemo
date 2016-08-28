package afred.javademo.thread;

/**
 * Created by Afred on 15/7/29.

 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Eric
 */
public class SleepThread extends Thread {

    private static int currentCount = 0;

    private static AtomicInteger count = new AtomicInteger(0);

    public SleepThread(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        while (count.get() < 30) {

            switch (count.get() % 3) {
                case 0:
                    if ("A".equals(getName())) {
                        printAndIncrease();
                    }
                    break;
                case 1:
                    if ("B".equals(getName())) {
                        printAndIncrease();
                    }
                    break;
                case 2:
                    if ("C".equals(getName())) {
                        printAndIncrease();
                    }
                    break;
            }
        }

    }

    private void printAndIncrease() {
        print();
        increase();
    }

    private void print() {
        System.out.println(getName());
        if ("C".equals(getName())) {
            System.out.println();
        }
    }

    private void increase() {
//        currentCount++;
        count.incrementAndGet();
    }

    public static void main(String[] args) {
        new SleepThread("A").start();
        new SleepThread("B").start();
        new SleepThread("C").start();
    }

}