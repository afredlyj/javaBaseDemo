package afred.javademo.spring.placeholder;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by afred on 16/6/14.
 */
public class Test {

    @org.junit.Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-placeholder.xml");

        Student student = context.getBean(Student.class);

        System.out.println(student.getName());
        System.out.println(student.getAge());
    }
}
