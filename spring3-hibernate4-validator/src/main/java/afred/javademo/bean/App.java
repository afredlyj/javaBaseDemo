package afred.javademo.bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-validator.xml");

        PersonForm form = new PersonForm(null, 0);

        MyService service = context.getBean(MyService.class);
        service.sayHi(form);

    }
}
