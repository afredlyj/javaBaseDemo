package afred.javademo.hystrix.annotation;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import afred.javademo.hystrix.MyThreadPoolExecutor;
import afred.javademo.hystrix.circuitbreaker.UserInfoData;

/**
 * Created by afred on 17/7/23.
 */
public class UserInfoManagerTest {



    /**
     * https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica
     */
    private static final Logger logger = LoggerFactory.getLogger(UserInfoManagerTest.class);

    @Test
    public void simple() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/hystrix-aop.xml");

        UserInfoManager userInfoManager = context.getBean(UserInfoManager.class);

        try {

            userInfoManager.get(12334);
        } catch (HystrixRuntimeException e) {
            logger.info("异常类型 : {}", e.getClass());
        } catch (Exception e) {
            Assert.fail();
        }

    }


    @Test
    public void fallback() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/hystrix-aop.xml");

        UserInfoManager userInfoManager = context.getBean(UserInfoManager.class);

        try {
            UserInfoData userInfoData = userInfoManager.getWithFallback(12334);
            Assert.assertTrue(userInfoData == UserInfoManager.FALLBACK_USER);
            return;
        } catch (Exception e) {
        }

        Assert.fail();

    }
    @Test
    public void getWrappedExeption() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/hystrix-aop.xml");

        UserInfoManager userInfoManager = context.getBean(UserInfoManager.class);

        try {

            userInfoManager.getUnWrappedExeption(12334);
        } catch (RuntimeException e) {
            logger.info("异常类型 : {}", e.getClass());
            return;
        } catch (Exception e) {
            logger.info("异常类型 : {}", e.getClass());
        }

        Assert.fail();

    }

    @Test
    public void threadpool() {

        HystrixPlugins.getInstance().registerConcurrencyStrategy(new HystrixConcurrencyStrategy() {
            @Override
            public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
                logger.debug("创建线程池");
                return new MyThreadPoolExecutor(corePoolSize.get(), maximumPoolSize.get(), keepAliveTime.get(), unit, workQueue);
            }
        });

        UserInfoManager.threadLocal.set("hello-world");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/hystrix-aop.xml");

        UserInfoManager userInfoManager = context.getBean(UserInfoManager.class);

        try {

            userInfoManager.get(12334);
        } catch (HystrixRuntimeException e) {
            logger.info("异常类型 : {} , {}", e.getClass(), UserInfoManager.threadLocal.get());
        } catch (Exception e) {
            logger.error("异常 : ", e);
            Assert.fail();
        }

    }

}
