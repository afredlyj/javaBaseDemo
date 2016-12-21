package afred.javademo.aop.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by afred on 16/10/11.
 */
public class LogService implements MethodBeforeAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

//        logger.info("method : {}, {}", method.getName(), Arrays.asList(args));

    }

    public static void main(String[] args) {
        System.out.println(512 >>> 4);
    }
}
