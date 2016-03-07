package afred.javademo.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by winnie on 2016-03-07 .
 */
public class Test {

    public static void main(String[] args) {

        UserService userService = new UserService();
        ProxyHandler proxyHandler = new ProxyHandler();

        //cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        //设置要创建动态代理的类
        enhancer.setSuperclass(userService.getClass());
        // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
        enhancer.setCallback(proxyHandler);
        UserService proxy = (UserService) enhancer.create();
        System.out.println(proxy.getUserNameById(1));

    }

}
