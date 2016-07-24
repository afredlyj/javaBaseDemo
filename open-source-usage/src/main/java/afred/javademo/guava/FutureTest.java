package afred.javademo.guava;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

    @Test
    public void transform() throws ExecutionException, InterruptedException {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));

        ListenableFuture<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {

                TimeUnit.SECONDS.sleep(2);
                logger.debug("compute");
                return "hello";
            }
        });

        final Thread mainThread =  Thread.currentThread();
        ListenableFuture<String> result = Futures.transform(future, new Function<String, String>() {
            @Override
            public String apply(String input) {
                logger.debug("transform : {}", input);

                Thread localThread = Thread.currentThread();
                Assert.assertTrue(mainThread == localThread);
                return "world";
            }
        });


        logger.debug("result : {}", result.get());

    }

    private ListenableFuture<String> getUserName(final String token) {


        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));

        ListenableFuture<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {

                TimeUnit.SECONDS.sleep(2);
                logger.debug("get user name by token : {}", token);
                return "username_" + token;
            }
        });

        return future;
    }

    private ListenableFuture<Integer> getAge(final String token) {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));

        ListenableFuture<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                TimeUnit.SECONDS.sleep(2);
                logger.debug("get age by token : {}", token);
                return RandomUtils.nextInt();
            }
        });

        return future;
    }

    @Test
    public void list() throws ExecutionException, InterruptedException {

        String token = "token";

        ListenableFuture<String> nameFuture = getUserName(token);
        ListenableFuture<Integer> ageFuture = getAge(token);

        ArrayList<? extends ListenableFuture<? extends Serializable>> listenableFutures = Lists.newArrayList(nameFuture, ageFuture);
        ListenableFuture<List<Serializable>> listListenableFuture = Futures.allAsList(listenableFutures);

        Futures.addCallback(listListenableFuture, new FutureCallback<List<Serializable>>() {
            @Override
            public void onSuccess(List<Serializable> result) {
                logger.debug("success : {}", result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        List<Serializable> result = listListenableFuture.get();

        for (Serializable each : result) {
            logger.debug("reault : {}", each);
        }
    }


}
