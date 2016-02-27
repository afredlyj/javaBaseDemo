package afred.javademo.method;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by winnie on 2016-02-27 .
 */
public class PersonForm {

    @NotNull
    @Size(max=64)
    private String name;

    @Min(0)
    private int age;

    public PersonForm(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
