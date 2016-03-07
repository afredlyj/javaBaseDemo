package afred.javademo.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by winnie on 2016-03-07 .
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object userService;

    public MyInvocationHandler(Object userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return method.invoke(userService, args);
    }
}
