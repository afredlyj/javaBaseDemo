package afred.javademo.proxy.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by winnie on 2016-03-07 .
 */
public class RpcFramework {

    public static <T> T export(final Object service, final String host, final int port) throws Exception {

        return (T) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                ObjectEchoClient client = new ObjectEchoClient();
                client.start("127.0.0.1", 8080);

                RpcMessage rpcMessage = new RpcMessage();
                rpcMessage.setMethodName(method.getName());
                rpcMessage.setParameterTypes(method.getParameterTypes());
                rpcMessage.setArgs(args);

                // TODO 处理Netty的返回
                client.send(rpcMessage);

                return null;
            }
        });
    }


}
