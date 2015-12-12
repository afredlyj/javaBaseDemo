package afred.demo.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Created by winnie on 15/12/12.
 */

public class People implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

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
        LogUtils.log("", "");
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
        People people = new People();
        people.setBeanFactory(null);
    }
}
