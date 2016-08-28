package afred.demo.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created with demo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-05-07 
 * Time: 13:52
 */
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
//        scheduleWithFixedDelay();
        scheduleAtFixedRate();
    }

    private static void singleThread() {

        ExecutorService service = Executors.newSingleThreadExecutor();

    }

    private static void mutilThreadWithOnceExecute() {

        logger.info("start ... ");
        ExecutorService service = Executors.newFixedThreadPool(5);

        int count = 10;
        final CountDownLatch latch = new CountDownLatch(count);
        Runnable task =  new Runnable() {
            @Override public void run() {

                try {
                    logger.info("enter ...");
                    TimeUnit.SECONDS.sleep(5);
                    logger.info("out ... ");
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < count; i++) {
            service.execute(task);
        }

        try {
            latch.await();
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("finish ... ");
    }

    private static void scheduleAtFixedRate() {

        logger.info("start ... ");
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        int count = 5;
        final CountDownLatch latch = new CountDownLatch(count);
        Runnable task =  new Runnable() {
            @Override public void run() {

                try {
                    logger.info("enter ...");
                    TimeUnit.SECONDS.sleep(5);
                    logger.info("out ... ");
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        service.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

        try {
            latch.await();
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("finish ... ");
    }


    private static void scheduleWithFixedDelay() {

        logger.info("start ... ");
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        int count = 5;
        final CountDownLatch latch = new CountDownLatch(count);
        Runnable task =  new Runnable() {
            @Override public void run() {

                try {
                    logger.info("enter ...");
                    TimeUnit.SECONDS.sleep(5);
                    logger.info("out ... ");
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        service.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);

        try {
            latch.await();
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("finish ... ");
    }

    private static void schedule() {

        ExecutorService service = Executors.newFixedThreadPool(10);

    }

}
