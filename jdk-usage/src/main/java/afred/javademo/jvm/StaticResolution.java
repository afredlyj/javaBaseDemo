package afred.javademo.jvm;

/**
 * Created by afred on 16/12/10.
 */
public class StaticResolution {

    public static void sayHello() {
        int i = 1;
        System.out.println("hello world" + i);
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }

}
