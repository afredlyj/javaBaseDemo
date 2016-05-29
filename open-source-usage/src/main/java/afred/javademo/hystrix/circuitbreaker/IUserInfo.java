package afred.javademo.hystrix.circuitbreaker;

/**
 * Created by afred on 16/5/29.
 */
public interface IUserInfo {

    UserInfoData queryUserInfo(int userId);

}
