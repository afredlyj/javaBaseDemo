package afred.javademo.hystrix.annotation;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import afred.javademo.hystrix.circuitbreaker.IUserInfo;
import afred.javademo.hystrix.circuitbreaker.UserInfoData;

/**
 * Created by afred on 17/7/23.
 */
public class UserInfoManager {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoManager.class);

    private afred.javademo.hystrix.circuitbreaker.IUserInfo userInfo;

    public IUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(IUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @HystrixCommand(fallbackMethod = "fallback", groupKey = "userinfo", threadPoolKey = "")
    public UserInfoData get(int userId) {
        logger.debug("user info : {}", userInfo);
        UserInfoData user = userInfo.queryUserInfo(userId);
        return user;
    }

    private UserInfoData fallback(int userId) {
        logger.debug("fall back");
        return new UserInfoData();
    }

}
