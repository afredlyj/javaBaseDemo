package afred.javademo.method;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 * 参考文档
 * http://jinnianshilongnian.iteye.com/blog/1495594
 */
public class App 
{
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-validator-method.xml");

        PersonForm form = new PersonForm(null, 0);

        MyService service = context.getBean(MyService.class);
        service.sayHi(form);

    }
}
