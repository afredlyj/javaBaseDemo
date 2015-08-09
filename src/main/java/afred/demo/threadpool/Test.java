package afred.demo.threadpool;

import java.util.concurrent.*;

/**
 * Created by winnie on 2015-05-09 .
 */
public class Test {

    public static void main(String[] args) {

//        new Test().fixedThreadPool();

        final SynchronousQueue<String> queue = new SynchronousQueue<String>();
//        System.out.printf("" + queue.offer("hello"));

        final CountDownLatch latch = new CountDownLatch(1);

        new Thread(new Runnable() {
//            @Override
            public void run() {
                try {
//                    latch.countDown();
                    System.out.printf("poll : " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        try {
            TimeUnit.SECONDS.sleep(1);
//            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("dddd" + queue.offer("hello"));



    }

    private void fixedThreadPool() {

        System.out.println("start ... ");

        int corePoolSize = 5;
        int maxPoplSize = 10;
        int queueSize = 5;

        ThreadPoolExecutor executor = new NotifyThreadPoolExecutor(corePoolSize, maxPoplSize, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(queueSize), new MyRejectedHandler());

        int taskCount = 100;

        final CountDownLatch latch = new CountDownLatch(taskCount);
        Runnable task = new Runnable() {
//            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    latch.countDown();
                    System.out.println("after sleep 2 sec ... ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < taskCount; i++) {
            executor.execute(task);
        }

        try {
            latch.await();
            executor.shutdown();
            System.out.println("finish ... ");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class MyRejectedHandler implements RejectedExecutionHandler {

//        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("reject task : " + r + ", " + executor);
        }
    }

    private class NotifyThreadPoolExecutor extends ThreadPoolExecutor {

        public NotifyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
//            super.beforeExecute(t, r);
            System.out.println("beforeExecute, thread : " + t.getName() + ", " + r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            System.out.println("afterExecute, task : " + r);
        }
    }



}
