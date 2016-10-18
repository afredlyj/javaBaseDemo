package afred.javademo.aop.proxy;

import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/10/11.
 */
public class HelloImpl implements IHello {
    @Override
    public void sayHi(String name) {
//        System.out.println("hello : " + name);
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
