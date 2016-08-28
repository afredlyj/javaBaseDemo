package afred.javademo.hibernate.infinite_loop;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by winnie on 2016-02-26 .
 */
public class Person {

    @NotNull
    private String name;

    @NotNull
    @Valid
    private Car car;

    public Person(String name, Car car) {
        this.name = name;
        this.car = car;
    }
}
