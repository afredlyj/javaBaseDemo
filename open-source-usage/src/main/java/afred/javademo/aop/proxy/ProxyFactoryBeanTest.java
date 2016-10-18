package afred.javademo.aop.proxy;

import org.junit.Test;
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

    @Test
    public void elapseTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("proxyfactorybean.xml");

        IHello bean = (IHello) context.getBean("logAdvisor");

        HelloImpl bean2 = context.getBean(HelloImpl.class);

        for (int i = 0; i < 10000; i++) {
            bean.sayHi("hello");
        }


        for (int i = 0; i < 10000; i++) {
            bean2.sayHi("hello");
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            bean.sayHi("hello");
        }

        long end = System.currentTimeMillis();

        System.out.println("aop elapse(ms) : " + (end - start));


        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            bean2.sayHi("hello");
        }

         end = System.currentTimeMillis();

        System.out.println("elapse(ms) : " + (end - start));

    }

}
