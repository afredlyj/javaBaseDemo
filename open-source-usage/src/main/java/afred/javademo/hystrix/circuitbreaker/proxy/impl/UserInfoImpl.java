package afred.javademo.hystrix.circuitbreaker.proxy.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import afred.javademo.hystrix.circuitbreaker.IUserInfo;
import afred.javademo.hystrix.circuitbreaker.UserInfoData;

/**
 * Created by afred on 16/10/3.
 */

public class UserInfoImpl implements IUserInfo {

    private static Logger logger = LoggerFactory.getLogger(UserInfoImpl.class);

    @Override
    public UserInfoData queryUserInfo(int userId) {

        logger.debug("query user info : {}", userId);

        return null;
    }
}
