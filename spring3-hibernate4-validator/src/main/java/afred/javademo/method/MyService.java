package afred.javademo.method;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by winnie on 2016-02-27 .
 */
@Validated
@Component
public class MyService {

    public void sayHi(@Valid @NotNull PersonForm form) {

    }

}
