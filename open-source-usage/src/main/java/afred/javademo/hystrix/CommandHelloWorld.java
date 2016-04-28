package afred.javademo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by winnie on 2016-04-17 .
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private static final Logger logger = LoggerFactory.getLogger(CommandHelloWorld.class);

    private String name;

    protected CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("example"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {

        logger.debug("执行 : {}, {}", name, Thread.currentThread().getName());
        return "hello " + name;
    }
}
