package afred.javademo.spi.impl;

import afred.javademo.spi.HelloService;

/**
 * Created by afred on 16/10/6.
 */
public class AfredHelloService implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("afred say hello");
    }
}
