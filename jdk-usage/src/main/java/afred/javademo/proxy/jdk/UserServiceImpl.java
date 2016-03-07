package afred.javademo.proxy.jdk;

/**
 * Created by winnie on 2016-03-07 .
 */
public class UserServiceImpl implements IUserService {

    @Override
    public String getNameById(int userId) {

        return "afred";
    }

    public String sayHi() {
        return "hi";
    }
}
