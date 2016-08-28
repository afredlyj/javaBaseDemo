package afred.javademo.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-03-02 
 * Time: 19:45
 */
public class ProxyHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(ProxyHandler.class);

    private Object sub;

    public ProxyHandler(Object sub) {
        this.sub = sub;
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before calling " + method);
        Object obj = method.invoke(sub, args);
        System.out.println("after calling " + method);
        return obj;
    }
}
