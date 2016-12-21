package afred.javademo.aop.autoproxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by afred on 16/11/23.
 */
@Service("logInterceptor")
public class LogInterceptor implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        logger.info("method invocation : {}", invocation.getMethod().getName());

        return invocation.proceed();
    }
}
