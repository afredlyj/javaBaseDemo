package afred.javademo.hystrix.annotation;

import com.netflix.hystrix.exception.HystrixRuntimeException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import afred.javademo.hystrix.circuitbreaker.UserInfoData;

/**
 * Created by afred on 17/7/23.
 */
public class UserInfoManagerTest {

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


}
