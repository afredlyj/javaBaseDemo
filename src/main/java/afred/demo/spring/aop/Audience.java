package afred.demo.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-12-29 
 * Time: 14:21
 */
public class Audience {

    private static final Logger logger = LoggerFactory.getLogger(Audience.class);

    public void takeSeats() {
        logger.info("占座");
    }

    public void takeSeats(String name) {
        logger.info("占座 : {}", name);
    }

    public void turnOffCellPhones() {
        logger.info("关闭手机");
    }

    public void applaund() {
        logger.info("鼓掌");
    }

    public void demandRefund(String name) {
        logger.info("退款 : {}", name);
    }

    public void watchPerformance(ProceedingJoinPoint joinPoint) {

        long start = System.currentTimeMillis();

        try {

            logger.info("请求参数 : {}", Arrays.asList(joinPoint.getArgs()));
            logger.info("{}", joinPoint.getKind());
            logger.info("{}", joinPoint.getSignature());

            Object object = joinPoint.proceed();
            logger.info("返回数据  : {}", object);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long end = System.currentTimeMillis();
        logger.debug("耗时 : {}", (end - start));

    }
}
