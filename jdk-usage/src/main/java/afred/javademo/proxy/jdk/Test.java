package afred.javademo.proxy.jdk;

import afred.javademo.proxy.ProxyUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by winnie on 2016-03-07 .
 */
public class Test {

    public static void main(String[] args) {

        InvocationHandler handler = new MyInvocationHandler(new UserServiceImpl());

        IUserService userService = (IUserService) Proxy.newProxyInstance(IUserService.class.getClassLoader(), new Class[]{IUserService.class}, handler);
        String name = userService.getNameById(1);
        System.out.println("result name : " + name);

        ProxyUtils.generateClassFile(userService.getClass(), "UserServiceProxy");
    }

}
