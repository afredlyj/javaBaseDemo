package afred.demo.validator.hibernate;

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
public class RentalCarTest {

    private static Validator validator;

    @BeforeClass
    public static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void test() {

        RentalCar car = new RentalCar( "Morris", "DD-AB-123", 1 );
//        car.setRentalStation("");

        Set<ConstraintViolation<RentalCar>> constraintViolations =
                validator.validate( car );

        assertEquals( 2, constraintViolations.size() );

    }

}
