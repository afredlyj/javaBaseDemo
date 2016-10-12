package afred.javademo.aop.proxy;

/**
 * Created by afred on 16/10/11.
 */
public class HelloImpl implements IHello {
    @Override
    public void sayHi(String name) {
        System.out.println("hello : " + name);
    }
}
