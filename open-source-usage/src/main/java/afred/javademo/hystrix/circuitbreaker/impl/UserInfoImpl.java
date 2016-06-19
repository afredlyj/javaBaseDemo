package afred.javademo.hystrix.circuitbreaker.impl;

import afred.javademo.hystrix.circuitbreaker.IUserInfo;
import afred.javademo.hystrix.circuitbreaker.UserInfoData;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by afred on 16/5/29.
 */
public class UserInfoImpl implements IUserInfo {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoImpl.class);

    private volatile boolean rpcfail = false;

    private volatile boolean httpfail = false;

    public void setRpcfail(boolean rpcfail) {
        this.rpcfail = rpcfail;
    }

    public void setHttpfail(boolean httpfail) {
        this.httpfail = httpfail;
    }

    @Override
    public UserInfoData queryUserInfo(int userId) {
        logger.debug("query user info : {}", userId);
        return new RpcUserInfoCommand(userId).execute();
    }

    private class RpcUserInfoCommand extends HystrixCommand<UserInfoData> {

        private final int userId;

        public RpcUserInfoCommand(int userId) {
            super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userinfo-rpc"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerRequestVolumeThreshold(1))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    .withCircuitBreakerErrorThresholdPercentage(1))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    .withCircuitBreakerSleepWindowInMilliseconds(1000)));
            this.userId = userId;
        }

        @Override
        protected UserInfoData run() throws Exception {
            logger.debug("try to get user info by rpc : {}", userId);

            if (rpcfail) {
                throw new RuntimeException("rpc request read time out : " + userId);
            }

            return dataBuild(userId, "rpc-" + RandomStringUtils.randomAlphabetic(8));
        }

        @Override
        protected UserInfoData getFallback() {

            logger.debug("rpc fail, try to http : {}", userId);

//            HttpUserInfoCommand httpUserInfoCommand = new HttpUserInfoCommand(userId);
//            return httpUserInfoCommand.execute();
            return  new UserInfoData();
        }
    }

    private UserInfoData dataBuild(int userId, String name) {
        UserInfoData data = new UserInfoData();
        data.setUserId(userId);
        data.setUserName(name);

        return data;
    }

    private class HttpUserInfoCommand extends HystrixCommand<UserInfoData> {

        private int userId;

        public HttpUserInfoCommand(int userId) {
            super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userinfo-http"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerRequestVolumeThreshold(10)));

            this.userId = userId;

        }

        @Override
        protected UserInfoData run() throws Exception {

            logger.debug("try to get user info by http : {}", userId);

            if (httpfail) {
                throw new RuntimeException("rpc request read time out : " + userId);
            }

            return dataBuild(userId, "http-" + RandomStringUtils.randomAlphabetic(8));

        }

//        @Override
//        protected UserInfoData getFallback() {
//            return super.getFallback();
//        }
    }


}
