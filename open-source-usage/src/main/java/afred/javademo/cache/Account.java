package afred.javademo.cache;

import java.io.Serializable;

/**
 * Created by afred on 17/2/11.
 */
public class Account implements Serializable{

    private int userId;

    private String name;

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
