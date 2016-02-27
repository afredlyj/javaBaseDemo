package afred.demo.validator.hibernate.infinite_loop;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by winnie on 2016-02-26 .
 */
public class CarTest {

    private static Validator validator;

    @BeforeClass
    public static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void carIsValid() {
        Car car = new Car( "Morris", "DD-AB-123", 2 );

        Person person = new Person("afred", car);
        car.setDriver(person);

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate(car);

        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void carIsNull() {
        Car car = new Car( "Morris", "DD-AB-123", 2 );

        Person person = new Person("afred", null);
        car.setDriver(person);

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate(car);

        assertEquals( 1, constraintViolations.size() );

    }


    @Test
    public void driverIsNull() {
        Car car = new Car( "Morris", "DD-AB-123", 2 );

        Person person = new Person("afred", null);
        car.setDriver(person);

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate(car);

        assertEquals( 1, constraintViolations.size() );

//        System.out.printf(constraintViolations.iterator().next().getMessage());

    }

}
