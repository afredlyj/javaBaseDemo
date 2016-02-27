package afred.demo.validator.hibernate;

import org.junit.BeforeClass;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

/**
 * Created by winnie on 2016-02-27 .
 */
public class MethodValidTest {

    private static ExecutableValidator validator;


    @BeforeClass
    public static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator().forExecutables();
    }



}
