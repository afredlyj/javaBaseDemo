package afred.demo.validator.hibernate.parameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.Date;

/**
 * Created by winnie on 2016-02-27 .
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParameterValidator implements ConstraintValidator<ConsistentDateParameters, Object[]> {

    private static final Logger logger = LoggerFactory.getLogger(ConsistentDateParameterValidator.class);

    public void initialize(ConsistentDateParameters constraintAnnotation) {

    }

    public boolean isValid(Object[] value, ConstraintValidatorContext context) {

        logger.info("value 长度 : {}", value.length);

        if (value.length != 2) {
            throw new IllegalArgumentException("illegal method signature");
        }

        //leave null-checking to @NotNull on individual parameters
        if ( value[0] == null || value[1] == null ) {
            return true;
        }

        if ( !( value[0] instanceof Date) || !( value[1] instanceof Date ) ) {
            throw new IllegalArgumentException(
                    "Illegal method signature, expected two " +
                            "parameters of type Date."
            );
        }

        return ( (Date) value[0] ).before( (Date) value[1] );
    }
}
