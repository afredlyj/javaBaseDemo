package afred.javademo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-05-05 
 * Time: 10:26
 */
public class RabbitMQTest {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        template.convertAndSend("hello");

        Thread.sleep(1000);
        ctx.destroy();
    }
}
