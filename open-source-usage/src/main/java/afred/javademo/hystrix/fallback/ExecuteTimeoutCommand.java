package afred.javademo.hystrix.fallback;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/5/29.
 */
public class ExecuteTimeoutCommand extends HystrixCommand<String> {

    public ExecuteTimeoutCommand(String name) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("execute-timeout"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000))
        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.defaultSetter().withCoreSize(2).withMaxQueueSize(10)));
    }

    @Override
    protected String run() throws Exception {

        TimeUnit.SECONDS.sleep(3);

        return "run";
    }

    @Override
    protected String getFallback() {
        return "fallback";
    }
}
