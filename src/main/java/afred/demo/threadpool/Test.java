package afred.demo.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by Afred on 15/5/10.
 */
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {

        ThreadPoolExecutor executor =  new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10)) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {

                logger.debug("thread : {}, task : {}", t.getName(), r);
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);

                logger.debug("task : {}", r);
//                logger.error("throwable : {}", t);
            }
        };

        executor.execute(new Runnable() {
            @Override
            public void run() {
                int i = 1 / 0;
            }
        });

//        Future f = executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                int i = 1 / 0;
//            }
//        });

//        try {
//            logger.debug("result : {}", f.get());
//        } catch (InterruptedException e) {
////            e.printStackTrace();
//        } catch (ExecutionException e) {
////            e.printStackTrace();
//        }

        try {
            TimeUnit.SECONDS.sleep(5);
            executor.shutdown();
        } catch (Exception e) {
//            e.printStackTrace();
        }

    }
}
