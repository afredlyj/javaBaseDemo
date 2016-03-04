package afred.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-03-02 
 * Time: 19:48
 */
public class Test {

    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        InvocationHandler handler = new ProxyHandler(helloService);

        IHelloService service = (IHelloService) Proxy.newProxyInstance(
                helloService.getClass().getClassLoader(), helloService.getClass().getInterfaces(), handler);

        String str = service.sayHello();
        System.out.println("response : " + str);

        str = service.sayHi();
        System.out.println("response : " + str);
    }

}
