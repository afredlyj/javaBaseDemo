package afred.javademo.hibernate.classlevel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by winnie on 2016-02-27 .
 */
public class ValidPassengerCountValidator implements ConstraintValidator<ValidPassengerCount, Car> {

    private static final Logger logger = LoggerFactory.getLogger(ValidPassengerCountValidator.class);

    public void initialize(ValidPassengerCount constraintAnnotation) {

    }

    public boolean isValid(Car value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        return value.getPassengers().size() <= value.getSeatCount();
    }
}
