package afred.javademo.aop.proxy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by afred on 16/10/11.
 */
public class ProxyFactoryBeanTest {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("proxyfactorybean.xml");

        IHello bean = (IHello) context.getBean("logAdvisor");

        bean.sayHi("hello");

    }

}
