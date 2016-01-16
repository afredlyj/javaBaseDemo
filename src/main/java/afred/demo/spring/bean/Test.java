package afred.demo.spring.bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by winnie on 15/12/12.
 */
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        People people = (People) context.getBean("people");

        System.out.println("people : " + people);
    }
}
