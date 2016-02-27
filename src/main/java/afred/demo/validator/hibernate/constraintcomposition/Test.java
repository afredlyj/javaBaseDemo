package afred.demo.validator.hibernate.constraintcomposition;

import afred.demo.validator.hibernate.CarTest;
import org.junit.BeforeClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by winnie on 2016-02-27 .
 */
public class Test {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @org.junit.Test
    public void test() {
        Car car = new Car(null);

        Set<ConstraintViolation<Car>> constraintViolationSet = validator.validate(car);
        CarTest.printViolation(constraintViolationSet);
    }

}
