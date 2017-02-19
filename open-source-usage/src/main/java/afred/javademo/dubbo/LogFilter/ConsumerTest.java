package afred.javademo.dubbo.logfilter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by afred on 16/10/6.
 */
public class ConsumerTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");

        HelloService helloService = context.getBean(HelloService.class);

        for (int i = 0; i < 100; i++) {
            try {
                System.out.println(helloService.sayHello("afred"));

            } catch (RuntimeException e) {

            }
        }

    }
}
