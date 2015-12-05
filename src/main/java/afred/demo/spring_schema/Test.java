package afred.demo.spring_schema;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by winnie on 15/12/5.
 */
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(context.getBean("defaltDateFormat"));

    }

}
