package afred.javademo.aop.autoproxy;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by afred on 16/11/23.
 */
public class BeanNameAutoProxyTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("auto-proxy.xml");

//        System.out.println("bean name : " + Arrays.asList(applicationContext.getBeanDefinitionNames()));
//
//        System.out.println("bean map : " + Arrays.asList(applicationContext.getBeansOfType(SayHiService.class)));

        SayHiService service = (SayHiService) applicationContext.getBean("sayHiService");

        service.sayHi("afred");

    }

}
