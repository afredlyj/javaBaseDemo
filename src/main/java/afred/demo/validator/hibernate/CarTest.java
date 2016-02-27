package afred.demo.validator.hibernate;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by winnie on 2016-02-26 .
 *
 * 参考文档 ：
 * http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-metadata-api
 *
 * Java SE 环境需要添加EL表达式依赖
 * http://hibernate.org/validator/documentation/getting-started/
 */
public class CarTest {

    /**
     * A Validator instance is thread-safe and may be reused multiple times.
     * It thus can safely be stored in a static field and be used in the test
     * methods to validate the different Car instances.
     */
    private static Validator validator;

    @BeforeClass
    public static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull() {
        Car car = new Car(null, "DD-AB", 4);
        Set<ConstraintViolation<Car>> constraintViolationSet = validator.validate(car);

        assertEquals( 2, constraintViolationSet.size() );
        assertEquals("不能为null", constraintViolationSet.iterator().next().getMessage() );

    }

    @Test
    public void licensePlateTooShort() {
        Car car = new Car( "Morris", "DAd", 3 );
        Person person = new Person("afred");
        car.setDriver(person);


        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate( car );
        printViolation(constraintViolations);
    }

    @Test
    public void seatCountTooLow() {
        Car car = new Car( "Morris", "DD-AB-123", 1 );

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "最小不能小于2",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void carIsValid() {
        Car car = new Car( "Morris", "DD-AB-123", 2 );

        Person person = new Person("afred");
        car.setDriver(person);

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate( car );

        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void validateProperty() {
        Car car = new Car( null, "DD-AB-123", 1 );

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validateProperty(car, "manufacturer");
        assertEquals( 1, constraintViolations.size() );

    }


    @Test
    public void validateValue() {
        Car car = new Car( null, "DD-AB-123", 1 );

        Set<ConstraintViolation<Car>> constraintViolations = validator.validateValue(
                Car.class,
                "manufacturer",
                null
        );
        assertEquals( 1, constraintViolations.size() );
    }

    private  static <T> void printViolation(Set<ConstraintViolation<T>> constraintViolations) {
        System.out.println("violations size : " + constraintViolations.size());
        Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> violation = iterator.next();
            System.out.println("error message : " + violation.getMessage());
        }
    }
}
