package afred.javademo.hystrix.dubbo;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.netflix.hystrix.HystrixCommandGroupKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by afred on 17/2/19.
 */
@Activate(
        group = {"consumer"}
)
public class HystrixFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(HystrixFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        DubboRemoteCommand command = new DubboRemoteCommand(HystrixCommandGroupKey.Factory.asKey("dubbo-remote"), invoker, invocation);
        return command.execute();

    }
}
