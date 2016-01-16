package afred.demo.spring.factorybean;

import afred.demo.spring.bean.People;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by winnie on 15/12/12.
 */
public class Test {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.printf("bean names : " + Arrays.asList(context.getBeanDefinitionNames()));

        AfredFactoryBean factoryBean = (AfredFactoryBean) context.getBean(AfredFactoryBean.class);
        factoryBean.isSingleton();

        People people = context.getBean("factoryBean", People.class);
        System.out.println(people.getName());

        PeopleManager manager = (PeopleManager) context.getBean(PeopleManager.class);
        System.out.println("manager people info : " + manager.getPeople().getName());
    }

}
