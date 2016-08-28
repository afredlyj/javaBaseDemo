package afred.javademo.annotation.tij;

import java.util.List;

/**
 * Created by Afred on 15/3/3.
 */
public class PasswordUtils {

    @UseCase(id = 47, description = "password must contain at least one numeric")
    public boolean validatePassword(String password) {
        return false;
    }

    @UseCase(id = 48, description = "hello world")
    public String encryptPassword(String password) {

        return password;
    }

    @UseCase(id = 49, description = "check for new password")
    public boolean checkForNewPassword(List<String> prevPasswords, String password) {

        return true;
    }
}
