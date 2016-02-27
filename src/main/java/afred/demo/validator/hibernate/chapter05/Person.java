package afred.demo.validator.hibernate.chapter05;

import javax.validation.constraints.NotNull;

/**
 * Created by winnie on 2016-02-27 .
 */
public class Person {

    @NotNull
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
