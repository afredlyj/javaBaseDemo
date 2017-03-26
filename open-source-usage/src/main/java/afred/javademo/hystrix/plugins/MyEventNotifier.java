package afred.javademo.hystrix.plugins;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by afred on 17/2/25.
 */
public class MyEventNotifier extends HystrixEventNotifier {

    private static final Logger logger = LoggerFactory.getLogger(MyEventNotifier.class);

    @Override
    public void markEvent(HystrixEventType eventType, HystrixCommandKey key) {
        super.markEvent(eventType, key);

        logger.debug("mark event : {}, {}", eventType, key);
    }

    @Override
    public void markCommandExecution(HystrixCommandKey key, HystrixCommandProperties.ExecutionIsolationStrategy isolationStrategy, int duration, List<HystrixEventType> eventsDuringExecution) {
        super.markCommandExecution(key, isolationStrategy, duration, eventsDuringExecution);

        logger.debug("command execution : {}, {}, {}", key, isolationStrategy, duration);
    }
}
