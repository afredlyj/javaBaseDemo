package afred.javademo.hystrix.circuitbreaker.proxy.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import afred.javademo.hystrix.circuitbreaker.IUserInfo;
import afred.javademo.hystrix.circuitbreaker.UserInfoData;

/**
 * Created by afred on 16/10/3.
 */
@Service("httpUserInfoService")
public class HttpUserInfoService implements IUserInfo {

    private static final Logger logger = LoggerFactory.getLogger(HttpUserInfoService.class);

    @Override
    public UserInfoData queryUserInfo(int userId) {

        logger.debug("http query user info : {}", userId);
//        return null;
        throw new RuntimeException("http exception");

    }
}
