package afred.javademo.hystrix.fallback;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/5/28.
 */
public class HelloCommandFallback extends HystrixCommand<String> {

    private static final Logger logger = LoggerFactory.getLogger(HelloCommandFallback.class);

    protected HelloCommandFallback(String group) {
        super(HystrixCommandGroupKey.Factory.asKey(group));
    }

    @Override
    protected String run() throws Exception {

        // dubbo invoke
        logger.debug("dubbo请求");
        throw new RuntimeException("dubbo请求异常");
    }

    @Override
    protected String getFallback() {

        // notify to change http invoker
        logger.error("执行fallback逻辑");
        return "http.hello";
    }
}
