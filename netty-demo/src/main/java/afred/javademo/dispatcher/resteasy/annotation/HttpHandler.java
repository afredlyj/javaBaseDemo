package afred.javademo.dispatcher.resteasy.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by afred on 16/8/18.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface HttpHandler {

    String value() default "";

}
