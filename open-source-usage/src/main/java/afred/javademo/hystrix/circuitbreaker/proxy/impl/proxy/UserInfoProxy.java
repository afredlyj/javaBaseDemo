package afred.javademo.hystrix.circuitbreaker.proxy.impl.proxy;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

import afred.javademo.hystrix.circuitbreaker.IUserInfo;
import afred.javademo.hystrix.circuitbreaker.UserInfoData;

/**
 * Created by afred on 16/10/3.
 */
public class UserInfoProxy {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoProxy.class);

    @Resource(name = "userInfoImpl")
    private IUserInfo targetService;

    @Resource(name = "httpUserInfoService")
    private IUserInfo fallbackService;

    public IUserInfo getTargetService() {
        return targetService;
    }

    public void setTargetService(IUserInfo targetService) {
        this.targetService = targetService;
    }

    public IUserInfo getFallbackService() {
        return fallbackService;
    }

    public void setFallbackService(IUserInfo fallbackService) {
        this.fallbackService = fallbackService;
    }

    public UserInfoData userinfo(final ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        final Object[] args = joinPoint.getArgs();

        logger.debug("start query user info");

        HystrixCommand<UserInfoData> hystrixCommand = new HystrixCommand<UserInfoData>(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userinfo-rpc"))) {
            @Override
            protected UserInfoData run() throws Exception {
                Object arg = args[0];
               return targetService.queryUserInfo((Integer) arg);
            }

            @Override
            protected UserInfoData getFallback() {

                HystrixCommand<UserInfoData> hystrixCommand =  new HystrixCommand<UserInfoData>(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userinfo-http"))) {
                    @Override
                    protected UserInfoData run() throws Exception {
                        Object arg = args[0];

                        UserInfoData userInfoData = fallbackService.queryUserInfo((Integer) arg);
                        return userInfoData;
                    }


                    @Override
                    protected UserInfoData getFallback() {
                        UserInfoData userInfoData = new UserInfoData();
                        userInfoData.setUserId(-1);
                        return userInfoData;
                    }
                };

                UserInfoData result = hystrixCommand.execute();
                return result;
            }
        };


        UserInfoData result = hystrixCommand.execute();
        long end = System.currentTimeMillis();
        logger.debug("耗时 : {}", (end - start));

        return result;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hystrix-aop.xml");

        IUserInfo service = (IUserInfo) context.getBean("userInfoImpl");

        for (int i = 0; i < 1000; i++) {
            UserInfoData userInfoData = service.queryUserInfo(1);
            System.out.println(userInfoData);

        }

    }
}
