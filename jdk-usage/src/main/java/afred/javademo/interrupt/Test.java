package afred.demo.interrupt;

import com.sun.org.apache.bcel.internal.generic.LADD;
import junit.framework.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-08-26 
 * Time: 10:00
 */
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @org.junit.Test public void test() {

        final CountDownLatch latch = new CountDownLatch(1);

        Thread t = new Thread(
                new Runnable() {
                    @Override public void run() {
                        latch.countDown();
                        logger.debug("begin run");

                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (Exception e) {
                            Assert.assertTrue(!Thread.currentThread().isInterrupted());
                            logger.error("exception : ", e);
                        }
                    }
                });
        t.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt();

        // 等待t执行
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test public void test4() {

        ExecutorService service = Executors.newFixedThreadPool(1, new DefaultThreadFactory());

        Runnable task = new Runnable() {
            @Override public void run() {
                logger.debug("hello : {}", Thread.currentThread());
                throw new NullPointerException("");
            }
        };

        // 直接报异常，并重新new线程
        for (int i = 0; i < 10; i++) {
            service.execute(task);
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test public void test3() {

        ExecutorService service = Executors.newFixedThreadPool(1, new DefaultThreadFactory());

        Runnable task = new Runnable() {
            @Override public void run() {
                logger.debug("hello : {}", Thread.currentThread());
                throw new NullPointerException("");
            }
        };

        // 并不会创建新的线程
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < 10; i++) {
           Future f = service.submit(task);
            list.add(f);
        }

        // 在get时才会报异常
        for (Future f : list) {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    @org.junit.Test public void test2() {

        ExecutorService service = Executors.newFixedThreadPool(1);
        Future future = service.submit(new Task());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        future.cancel(true);

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        service.submit(
                new Runnable() {
                    @Override public void run() {
                        logger.debug("hello : {}", Thread.currentThread());
                        throw new NullPointerException("");
                    }
                });


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test public void test5() {

        final CountDownLatch latch = new CountDownLatch(1);

        Thread t = new Thread(
                new Runnable() {
                    @Override public void run() {

                        try {
                            latch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }

                        try {

                            TimeUnit.SECONDS.sleep(10);
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                });

        t.start();

        t.interrupt();
        latch.countDown();

        try {
            TimeUnit.SECONDS.sleep(20);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    static volatile boolean ready = false;

    @org.junit.Test public void test6() throws InterruptedException {

        Thread t = new Thread () {
            public void run () {
                while (!ready) {
                }
                try {
                    Thread.sleep (1);
                    System.out.println ("Not thrown!");
                }
                catch (InterruptedException e) {
                    System.out.println ("Thrown!");
                }
            }
        };
        t.start ();
        t.interrupt (); // remove this line to change the output
        ready = true;
        Thread.sleep (100);

    }

    @org.junit.Test public void test8() throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(5);

        List<Future> futures = new ArrayList<Future>();
        for (int i = 0; i < 5; i++) {

            Future f = service.submit(new Task1());
            futures.add(f);
        }

        TimeUnit.SECONDS.sleep(5);

        for (Future f : futures) {
            f.cancel(true);
        }

        TimeUnit.SECONDS.sleep(10);

    }



    @org.junit.Test public void test7() throws InterruptedException {

        ExecutorService service = Executors.newSingleThreadExecutor();



        Future f = service.submit(
                new Runnable() {
                    @Override public void run() {

                        try {
                            System.out.println("start");
                            while (!ready) {
                            }
                            try {
                                Thread.sleep(1);
                                System.out.println("Not thrown!");
                            } catch (InterruptedException e) {
                                System.out.println("Thrown!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

//        try {
//            try {
//                f.get(2, TimeUnit.SECONDS);
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            }
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        // 保证cancel之前任务能执行
        TimeUnit.SECONDS.sleep(2);
        System.out.println("status : " + f.isDone());

        f.cancel(true);

        TimeUnit.SECONDS.sleep(5);


        ready = true;

        TimeUnit.SECONDS.sleep(5);


    }

    static class Task1 implements Runnable {

        @Override public void run() {
            logger.debug("begin run : {}", Thread.currentThread());
                    try {

                        while (true) {
                            logger.debug("try to write mysql : {}", Thread.currentThread());

                            TimeUnit.SECONDS.sleep(10);
                        }

                    } catch (InterruptedException e) {
                        logger.error("exception", e);
                    }

        }
    }

    static class Task implements Runnable {

        @Override public void run() {
            logger.debug("begin run : {}", Thread.currentThread());

            boolean interrupted = false;
            try {
                while (!interrupted) {
                    try {
                        // 继续工作
                        //
                        logger.debug("try to write mysql : {}", Thread.currentThread());

                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        logger.error("exception", e);
                        interrupted = true;
                        throw new RuntimeException(e);
                    }
                }
            } finally {
                // 设置中断状态
                if (interrupted) {
                    Thread.currentThread().interrupt();

                }
            }

        }
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {

            logger.debug("try to new thread");
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(
                int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
        }

        @Override protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }
    }

}
