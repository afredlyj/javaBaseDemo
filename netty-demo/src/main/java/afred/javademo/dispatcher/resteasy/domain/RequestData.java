package afred.javademo.dispatcher.resteasy.domain;

import java.io.Serializable;

/**
 * Created by afred on 16/8/19.
 */
public class RequestData implements Serializable {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "data='" + data + '\'' +
                '}';
    }
}
