package afred.demo.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created with demo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-05-12 
 * Time: 14:27
 */
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    /**
     * wait和notify方法使用说明参见接口文档
     */
    private static void testWait() {

        final String lock = "helloworld";

        Thread thread = new Thread(
                new Runnable() {
                    @Override public void run() {

                        logger.debug("enter thread ... ");
                        synchronized (lock) {
                            try {
                                logger.debug("before wait ... ");
                                lock.wait();
                                logger.debug("after wait ... ");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        thread.interrupt();

//        synchronized (lock) {
//            logger.debug("before notify");
//            lock.notify();
//
//            logger.debug("after notify");
//
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//
//            logger.debug("after main synchronized");
//        }

        logger.debug("finish test ... ");
    }

    public static void main(String[] args) {

        testWait();

    }

}
