package afred.demo.validator.hibernate.chapter05;

import afred.demo.validator.hibernate.CarTest;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by winnie on 2016-02-27 .
 */
public class GroupTest {

    private static Validator validator;

    /**
     * 分组三组：
     * Default group : Person.name, Car.manufacturer, Car.licensePlate 和 Car.seatCount
     * DriverChecks : Driver.age 和 Driver.hasDrivingLicense
     * CarChecks : Car.passedVehicleInspection
     */

    @BeforeClass
    public static void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void drive() {

        Car car = new Car( "Morris", "DD-AB-123", 2 );

        // default group
        Set<ConstraintViolation<Car>> constraintViolations = validator.validate( car );

        assertEquals( 0, constraintViolations.size() );


        // CarChecks group
        constraintViolations = validator.validate( car, CarChecks.class );
        CarTest.printViolation(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "The car has to pass the vehicle inspection first",
                constraintViolations.iterator().next().getMessage()
        );

        car.setPassedVehicleInspection(true);
        constraintViolations = validator.validate( car, CarChecks.class );
        assertEquals(0, constraintViolations.size());

        Driver john = new Driver( "John Doe" );
        john.setAge( 18 );
        car.setDriver( john );
        constraintViolations = validator.validate( car, DriverChecks.class );
        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "You first have to pass the driving test",
                constraintViolations.iterator().next().getMessage()
        );

        // ok, John passes the test
        john.passedDrivingTest( true );
        assertEquals( 0, validator.validate( car, DriverChecks.class ).size() );

        // just checking that everything is in order now
        assertEquals(
                0, validator.validate(
                        car,
                        Default.class,
                        CarChecks.class,
                        DriverChecks.class
                ).size()
        );
    }

    @Test
    public void testOrderedChecks() {
        Car car = new Car( "Morris", "DD-AB-123", 2 );
        car.setPassedVehicleInspection( true );

        Driver john = new Driver( "John Doe" );
        john.setAge( 18 );
        john.passedDrivingTest( true );
        car.setDriver( john );

        assertEquals( 0, validator.validate( car, OrderedChecks.class ).size() );
    }

}
