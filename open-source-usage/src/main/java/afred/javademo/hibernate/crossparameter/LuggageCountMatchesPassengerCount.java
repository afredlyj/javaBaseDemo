package afred.javademo.hibernate.crossparameter;

import javax.validation.*;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by winnie on 2016-02-27 .
 */
@Target({METHOD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = LuggageCountMatchesPassengerCount.Validator.class)
@Documented
public @interface LuggageCountMatchesPassengerCount {

    String message() default "LuggageCountMatchesPassengerCount.message";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    ConstraintTarget validationAppliesTo() default ConstraintTarget.IMPLICIT;

    int piecesOfLuggagePerPassenger() default 1;

    @SupportedValidationTarget({
            ValidationTarget.PARAMETERS,
            ValidationTarget.ANNOTATED_ELEMENT
    })
    public static class Validator
            implements ConstraintValidator<LuggageCountMatchesPassengerCount, Object[]> {
        @Override
        public void initialize(LuggageCountMatchesPassengerCount constraintAnnotation) {

        }

        @Override
        public boolean isValid(Object[] value, ConstraintValidatorContext context) {
            return false;
        }
    }

}
