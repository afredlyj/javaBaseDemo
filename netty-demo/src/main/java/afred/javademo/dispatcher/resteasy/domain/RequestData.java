package afred.javademo.dispatcher.resteasy.domain;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by afred on 16/8/19.
 */

public class RequestData implements Serializable {

    @Size(min = 10, max = 12)
    private String data;

    @NotNull
    @Min(1)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
                ", id=" + id +
                '}';
    }
}
