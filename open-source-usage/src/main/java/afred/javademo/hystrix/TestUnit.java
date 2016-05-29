package afred.javademo.hystrix;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 2016-04-17 .
 */
public class TestUnit {

    private static final Logger logger = LoggerFactory.getLogger(TestUnit.class);
    @Test
    /**
     * 同步
     */
    public void test1() {

        Assert.assertEquals("hello afred", new CommandHelloWorld("afred").execute());
    }

    @Test
    /**
     * future
     */
    public void test2() throws ExecutionException, InterruptedException {

        Future<String> future = new CommandHelloWorld("afred").queue();
        logger.debug("future : {}", future);
        Assert.assertEquals("hello afred", future.get());

    }



    @Test public void testObservable() throws InterruptedException {
        Observable<String> o = new CommandHelloWorld("hello").observe();

        Observable<String> o2 = new CommandHelloWorld("world").observe();

        o.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                logger.debug("on completed");
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error("exception : {}", throwable);
            }

            @Override
            public void onNext(String s) {
                logger.debug("on next : {}", s);
            }
        });

        o.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                logger.debug("call : {}", s);
            }
        });

        TimeUnit.SECONDS.sleep(2);
     }

}
