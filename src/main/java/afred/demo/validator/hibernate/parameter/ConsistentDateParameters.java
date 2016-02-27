package afred.demo.validator.hibernate.parameter;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * Created by winnie on 2016-02-27 .
 */
@Constraint(validatedBy = ConsistentDateParameterValidator.class)
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConsistentDateParameters {

    String message() default "{afred.demo.validator.hibernate.parameter.ConsistentDateParameters.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
