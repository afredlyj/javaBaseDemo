package afred.javademo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-03-03 
 * Time: 16:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodUrl {
    String value();
}
