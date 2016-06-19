package afred.javademo.guava;

import com.google.common.util.concurrent.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/5/26.
 */
public class FutureTest {

    private static Logger logger = LoggerFactory.getLogger(FutureTest.class);

    @Test
    public void callback() throws InterruptedException {


        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));

        ListenableFuture<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {

                logger.debug("request caller");

                TimeUnit.SECONDS.sleep(5);
                return "hello";
            }
        });

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                logger.debug("get result success : {}", result);

                // TODO: 16/5/26 获取到前置条件之后,再继续走流程
                countDownLatch.countDown();

            }

            @Override
            public void onFailure(Throwable t) {

                logger.debug("get result exception : {}", t);
            }
        });

        countDownLatch.await();
    }

    @Test
    public void listener() throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        ListenableFuture<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {

                logger.debug("request caller");
                TimeUnit.SECONDS.sleep(5);

                return "hello";
            }
        });

        future.addListener(new Runnable() {
            @Override
            public void run() {
                // TODO: 16/5/26
                logger.debug("listener run");

                countDownLatch.countDown();
            }
        }, Executors.newSingleThreadExecutor());


        countDownLatch.await();
    }


}
