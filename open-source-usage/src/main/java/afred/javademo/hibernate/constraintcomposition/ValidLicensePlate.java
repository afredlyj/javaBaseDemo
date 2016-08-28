package afred.javademo.hibernate.constraintcomposition;

import afred.javademo.hibernate.custom.CaseMode;
import afred.javademo.hibernate.custom.CheckCase;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * Created by winnie on 2016-02-27 .
 */
@NotNull
@Size(min = 2, max = 14)
@CheckCase(CaseMode.UPPER)
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { })
@Documented
public @interface ValidLicensePlate {

    String message() default "{ValidLicensePlate.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
