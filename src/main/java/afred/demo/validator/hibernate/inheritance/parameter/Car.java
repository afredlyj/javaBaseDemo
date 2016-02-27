package afred.demo.validator.hibernate.inheritance.parameter;

import javax.validation.constraints.Max;

/**
 * Created by winnie on 2016-02-27 .
 */
public class Car implements Vehicle  {
    @Override
    public void drive(@Max(75) int speedInMph) {

    }

}
