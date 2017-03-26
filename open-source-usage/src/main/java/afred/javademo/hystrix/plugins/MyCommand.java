package afred.javademo.hystrix.plugins;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by afred on 17/2/25.
 */
public class MyCommand extends HystrixCommand<Void> {

    private static final Logger logger = LoggerFactory.getLogger(MyCommand.class);

    public MyCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected Void run() throws Exception {
        throw new RuntimeException();
    }

    @Override
    protected Void getFallback() {

        logger.debug("get fallback");
//        return null;

        throw new RuntimeException();
    }
}
