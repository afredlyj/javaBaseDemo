package afred.javademo.spring.placeholder;

import java.io.Serializable;

/**
 * Created by afred on 16/6/14.
 */
public class Student implements Serializable {

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int age;



}
