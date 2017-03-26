package afred.javademo.hystrix.plugins;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.HystrixPlugins;

import org.junit.Test;

/**
 * Created by afred on 17/2/25.
 */
public class PluginsTest {

    @Test
    public void test() {
        HystrixPlugins.getInstance().registerEventNotifier(new MyEventNotifier());

//        HystrixPlugins.getInstance().registerMetricsPublisher();
//        HystrixPlugins.getInstance().registerPropertiesStrategy();
        MyCommand command = new MyCommand(HystrixCommandGroupKey.Factory.asKey("hello"));

        command.execute();
    }

}
