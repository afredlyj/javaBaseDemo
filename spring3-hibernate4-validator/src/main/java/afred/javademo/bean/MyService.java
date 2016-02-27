package afred.javademo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by winnie on 2016-02-27 .
 */
@Component
public class MyService {

    @Autowired
    private Validator validator;

    public void sayHi(PersonForm form) {

        Set<ConstraintViolation<PersonForm>> constraintViolations = validator.validate(form);

        printViolation(constraintViolations);

    }

    public static <T> void printViolation(Set<ConstraintViolation<T>> constraintViolations) {
        System.out.println("violations size : " + constraintViolations.size());
        Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> violation = iterator.next();
            System.out.println("error message : " + violation.getMessage());
        }
    }



}
