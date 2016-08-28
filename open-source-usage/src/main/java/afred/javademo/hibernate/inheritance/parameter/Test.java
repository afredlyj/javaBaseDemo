package afred.demo.validator.hibernate.inheritance.parameter;

import afred.demo.validator.hibernate.CarTest;
import org.junit.BeforeClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by winnie on 2016-02-27 .
 */
public class Test {

    private static ExecutableValidator validator;


    @BeforeClass
    public static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator().forExecutables();
    }

    @org.junit.Test
    public void test() throws NoSuchMethodException {
        Car car = new Car();

        Method method = Car.class.getMethod("drive", int.class);
        Object[] parameterValues = {80};
        Set<ConstraintViolation<Car>> violations = validator.validateParameters(
                car,
                method,
                parameterValues
        );

        CarTest.printViolation(violations);
    }
}
