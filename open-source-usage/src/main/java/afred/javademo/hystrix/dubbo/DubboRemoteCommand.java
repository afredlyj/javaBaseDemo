package afred.javademo.hystrix.dubbo;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcResult;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by afred on 17/2/19.
 */
public class DubboRemoteCommand extends HystrixCommand<Result> {

    private static final Logger logger = LoggerFactory.getLogger(DubboRemoteCommand.class);

    private Invoker<?> invoker;

    private Invocation invocation;

    protected DubboRemoteCommand(HystrixCommandGroupKey group, Invoker<?> invoker, Invocation invocation) {

        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("dubbo-remote"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerRequestVolumeThreshold(5)));

        this.invoker = invoker;
        this.invocation = invocation;
    }

    @Override
    protected Result run() throws Exception {

        logger.debug("执行正常请求 : {}, {}", invoker, invocation);
       return invoker.invoke(invocation);
    }

    @Override
    protected Result getFallback() {

        logger.debug("执行降级逻辑");

        RpcResult rpcResult = new RpcResult();
        rpcResult.setAttachments(invocation.getAttachments());
        rpcResult.setException(new RuntimeException("dubbo 远程调用降级"));
        return rpcResult;
    }
}
