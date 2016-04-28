package afred.javademo.hystrix;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

}
