package afred.demo.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-12-29 
 * Time: 14:47
 */
public class Test {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");

        Performer performer =  context.getBean(Performer.class);
//        performer.perform1("hello");
        performer.perform();

//        performer.doPerform("猴子捞月");


//        Volunteer volunteer = (Volunteer) context.getBean("volunteer");
//        volunteer.thinkOfSomething("hello");

    }

}
