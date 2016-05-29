package afred.javademo.hystrix.fallback;

import org.junit.Assert;

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
}
