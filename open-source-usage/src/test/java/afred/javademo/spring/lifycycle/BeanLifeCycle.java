package afred.javademo.spring.lifycycle;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Arrays;

import afred.javademo.spring.bean.People;
import afred.javademo.spring.factorybean.AfredFactoryBean;
import afred.javademo.spring.factorybean.PeopleManager;

/**
 * Created by winnie on 2016-04-10 .
 */
public class BeanLifeCycle {

    @Test public void test() {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src\\test\\java\\afred\\javademo\\spring\\lifycycle\\applicationContext.xml");


        System.out.printf("bean names : " + Arrays.asList(context.getBeanDefinitionNames()));

        AfredFactoryBean factoryBean = (AfredFactoryBean) context.getBean(AfredFactoryBean.class);
        factoryBean.isSingleton();

        People people = context.getBean("factoryBean", People.class);
        System.out.println(people.getName());

        PeopleManager manager = (PeopleManager) context.getBean(PeopleManager.class);
        System.out.println("manager people info : " + manager.getPeople().getName());
    }



}
