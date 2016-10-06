package afred.javademo.dubbo.logfilter;


import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by afred on 16/10/6.
 */
public class RequestLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {


        logger.debug("记录请求日志");

        Object[] arguments = invocation.getArguments();
        StringBuilder stringBuilder = new StringBuilder(256);
        for (Object object : arguments) {
            stringBuilder.append(com.alibaba.fastjson.JSON.toJSON(object));
            stringBuilder.append(",");
        }

        logger.info("request data : {}", stringBuilder);

        return invoker.invoke(invocation);
    }
}
