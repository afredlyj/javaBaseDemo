package afred.demo.thread;

import junit.framework.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Afred on 15/7/27.
 */
public class ThreadSchedule {

//    public static void main(String[] args) {
//
//        Thread thread1 = new Thread(new Task());
//        System.out.println("thread state : " + thread1.getState());
//
//    }

    @Test
    public void newState() {
        Thread thread = new Thread(new Task(new Object()));
        Assert.assertTrue(thread.getState() == Thread.State.NEW);
    }

    @Test
    public void runnableState() {
        Thread thread = new Thread(new Task(new Object()));

        Assert.assertTrue(thread.getState() != Thread.State.RUNNABLE);

        thread.start();

        Assert.assertTrue(thread.getState() == Thread.State.RUNNABLE);
    }

    @Test
    public void blockedState() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);

        final Object obj = new Object();
        Thread t1 = new Thread(new Task2(obj, latch));

        Thread t2 = new Thread(new Task2(obj, latch));

        t1.start();
        Assert.assertTrue(t1.getState() == Thread.State.RUNNABLE);

        t2.start();

        latch.await();

        try {
            // 保证main线程没有提前退出
            TimeUnit.SECONDS.sleep(5);

        } catch (Exception e) {
        }

        System.out.println("t2 state : " + t2.getState());

        Assert.assertTrue(t2.getState() == Thread.State.BLOCKED);
    }


    @Test
    public void blockedState2() {
        final Object obj = new Object();
        Thread t = new Thread(new Task3(obj));
        t.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        synchronized (obj) {
            obj.notify();

            // 此时锁并没有释放，线程t调用wait之后，重入synchronized方法，处于BLOCKED状态
            System.out.println("thread state : " + t.getState());
            Assert.assertTrue(t.getState() == Thread.State.BLOCKED);

            try {
                TimeUnit.SECONDS.sleep(3);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        try {
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void waitingState() {
        Thread t = new Thread(new Task3(new Object()));
        t.start();

        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (Exception e) {
        }

        // 线程t调用wait之后，处于WAITING状态
        System.out.println("thread state : " + t.getState());
        Assert.assertTrue(t.getState() == Thread.State.WAITING);

    }

    @Test
    public void timewaitingState() {
        final Object obj = new Object();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    try {
                        obj.wait(10 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();
        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (Exception e) {

        }

        // 调用wait(timeout) ，线程t处于TIMED_WAITING状态
        System.out.println("thread state : " + t.getState());
        Assert.assertTrue(t.getState() == Thread.State.TIMED_WAITING);
    }

    @Test
    public void terminatedState() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        t.start();
        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (Exception e) {

        }

        Assert.assertTrue(t.getState() == Thread.State.TERMINATED);

        System.out.println("thread state : " + t.getState());

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new NullPointerException();
            }
        });
        t.start();
        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (Exception e) {

        }

        Assert.assertTrue(t.getState() == Thread.State.TERMINATED);

        System.out.println("thread state : " + t.getState());

    }

}

class Task3 implements Runnable {

    private Object monitor;

    public Task3(Object object) {
        this.monitor = object;
    }

    @Override
    public void run() {
        synchronized (monitor) {

            System.out.println("before wait : " + Thread.currentThread().getState());

            try {
                monitor.wait();
                System.out.println("after wait : " + Thread.currentThread().getState());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("out of synchronized : " + Thread.currentThread().getState());
        }
    }
}

class Task2 implements Runnable {


    private Object monitor;

    private CountDownLatch latch;

    public Task2(Object obj, CountDownLatch latch) {

        monitor = obj;
        this.latch = latch;
    }

    @Override
    public void run() {

        latch.countDown();

        System.out.println("thread name : " + Thread.currentThread().getName());

        synchronized (monitor) {

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        System.out.println("finish");

    }
}

class Task implements Runnable {

    private Object monitor;

    public Task(Object obj) {
        monitor = obj;
    }

    @Override
    public void run() {
        System.out.println("thread name : " + Thread.currentThread().getName());

        synchronized (monitor) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        System.out.println("finish");

    }
}
