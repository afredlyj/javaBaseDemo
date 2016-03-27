package afred.javademo.proxy.rpc;

import java.io.Serializable;

/**
 * Created by winnie on 2016-03-08 .
 */
public class RpcMessage implements Serializable {

    private Class<?>[] parameterTypes;

    private String methodName;

    private Object[] args;

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
