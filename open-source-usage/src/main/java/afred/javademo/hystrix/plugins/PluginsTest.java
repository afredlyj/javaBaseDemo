package afred.javademo.hystrix.plugins;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 17/2/25.
 */
public class PluginsTest {

    @Test
    public void test() {
        HystrixPlugins.getInstance().registerEventNotifier(new MyEventNotifier());

        HystrixPlugins.getInstance().registerConcurrencyStrategy(new HystrixConcurrencyStrategy() {
            @Override
            public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
                return super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
            }

            @Override
            public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
                return super.getBlockingQueue(maxQueueSize);
            }

            @Override
            public <T> Callable<T> wrapCallable(Callable<T> callable) {
                return super.wrapCallable(callable);
            }

            @Override
            public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
                return super.getRequestVariable(rv);
            }
        });

        HystrixRequestContext.initializeContext();

//        HystrixPlugins.getInstance().registerMetricsPublisher();
//        HystrixPlugins.getInstance().registerPropertiesStrategy();
        MyCommand command = new MyCommand(HystrixCommandGroupKey.Factory.asKey("hello"));

        command.execute();
    }

}
