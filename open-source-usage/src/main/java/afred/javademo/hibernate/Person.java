package afred.javademo.hibernate;

import javax.validation.constraints.NotNull;

/**
 * Created by winnie on 2016-02-26 .
 */
public class Person {

    @NotNull
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
