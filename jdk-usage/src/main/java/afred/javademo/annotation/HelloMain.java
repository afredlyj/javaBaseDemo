package afred.javademo.annotation;

/**
 * Created by afred on 17/6/17.
 */
public class HelloMain {

    @MyMethodAnnotation(layoutId = 100,viewType = 1,viewHolder = "com.example.test")
    public void fun(){

    }

    public static void main(String[] args) {
        new HelloMain().fun();

    }

}
