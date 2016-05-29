package afred.javademo.hystrix.circuitbreaker;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by afred on 16/5/28.
 */
public class UserInfoCommand extends HystrixCommand<String> {


    private static final Logger logger = LoggerFactory.getLogger(UserInfoCommand.class);

    private final int userId;

    private boolean throwException;

    private static AtomicInteger runMethodCounter = new AtomicInteger();

    private static AtomicInteger fallbackMethodCounter = new AtomicInteger();

    public UserInfoCommand(int userId, boolean throwException) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userinfo"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerRequestVolumeThreshold(5)));

        this.userId = userId;

        this.throwException = throwException;
    }

    @Override
    protected String run() throws Exception {

        runMethodCounter.incrementAndGet();

        if (throwException) {
            throw new RuntimeException("throw runtime exception : " + userId);
        } else {
            return String.valueOf(userId);
        }
    }

    public static int runTimes() {
        return runMethodCounter.get();
    }

    public static int fallbackTimes() {
        return fallbackMethodCounter.get();
    }

    @Override
    protected String getFallback() {

        fallbackMethodCounter.incrementAndGet();
        logger.debug("fall back method called : {}", userId);
        return "-1";
    }
}
