package afred.javademo.hystrix.annotation;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by afred on 17/7/23.
 */
public class UserInfoManagerTest {

    @Test
    public void simple() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/hystrix-aop.xml");

        UserInfoManager userInfoManager = context.getBean(UserInfoManager.class);

        userInfoManager.get(12334);


    }

}
