package afred.javademo.hystrix.fallback;

import org.junit.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/5/28.
 */
public class Test {

    @org.junit.Test
    public void fallback() {
        HelloCommandFallback commandFallback = new HelloCommandFallback("fallback-demo");
        String result = commandFallback.execute();
        Assert.assertTrue(result.equals("http.hello"));
    }

    @org.junit.Test
    public void executeTimeout() {
        ExecuteTimeoutCommand command = new ExecuteTimeoutCommand("afred");

        Assert.assertTrue(command.execute().equals("fallback"));
    }

    @org.junit.Test
    public void queue() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    ExecuteTimeoutCommand command = new ExecuteTimeoutCommand("afred");

                    command.execute();
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(1000);
    }
}
