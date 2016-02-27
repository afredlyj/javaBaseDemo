package afred.demo.validator.hibernate.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by winnie on 2016-02-27 .
 */
public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private static final Logger logger = LoggerFactory.getLogger(CheckCaseValidator.class);

    private CaseMode caseMode;

    public void initialize(CheckCase constraintAnnotation) {
        this.caseMode = constraintAnnotation.value();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {

        logger.info("validate value : {}", value);

        // 如果null非法，交给@NotNull
        if (null == value) {
            return true;
        }

        boolean isValid = false;
        switch (caseMode) {
            case UPPER:
                isValid = value.equals(value.toUpperCase());
                break;
            case LOWER:
                isValid = value.equals(value.toLowerCase());
                break;
        }

        if (!isValid) {
            logger.info("default message template : {}", context.getDefaultConstraintMessageTemplate());
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{afred.demo.validator.hibernate.custom.CheckCase.message}").addConstraintViolation();
        }

        return isValid;
    }
}
