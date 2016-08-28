package afred.javademo.hibernate.chapter05;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;

/**
 * Created by winnie on 2016-02-27 .
 */
public class Driver extends Person {


    public Driver(String name) {
        super(name);
    }

    @Min(value = 18, message = "> 18 to drive car", groups = DriverChecks.class)
    private int age;

    @AssertTrue(
            message = "You first have to pass the driving test",
            groups = DriverChecks.class
    )
    private boolean hasDrivingLicense;

    public void passedDrivingTest(boolean b) {
        hasDrivingLicense = b;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

