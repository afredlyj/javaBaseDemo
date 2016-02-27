package afred.demo.validator.hibernate.classlevel;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
/**
 * Created by winnie on 2016-02-27 .
 */
public class ClassLevelConstraintTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testClassLevelConstraint() {
        List<Person> list = Arrays.asList(new Person("Alice"), new Person("Bob"), new Person("Bill"));
        Car car = new Car(2, list);

        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "There must be not more passengers than seats.",
                constraintViolations.iterator().next().getMessage()
        );
    }

}
