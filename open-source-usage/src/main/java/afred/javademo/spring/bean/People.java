package afred.javademo.spring.bean;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by winnie on 15/12/12.
 */
public class People implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(People.class);

    private String name;

    private int age;

    public People() {

    }

    public People(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        System.out.println("bean factory : " + beanFactory);
        Assert.state(beanFactory instanceof DefaultListableBeanFactory);
    }

    public void setBeanName(String name) {
        LogUtils.log("设置bean name : {}", name);
    }

    public void destroy() throws Exception {
        LogUtils.log("", "");

    }

    public void init() {
//        this.name = "afred";
//        this.age = 18;
        LogUtils.log("name : {}, age : {}", name, age);
    }

    public void afterPropertiesSet() throws Exception {
        LogUtils.log("", "");
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");

    }
}
