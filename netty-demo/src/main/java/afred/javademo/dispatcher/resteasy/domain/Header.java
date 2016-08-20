package afred.javademo.dispatcher.resteasy.domain;

import javax.ws.rs.HeaderParam;
import java.io.Serializable;

/**
 * Created by afred on 16/8/20.
 */
public class Header implements Serializable {

    @HeaderParam("Content-Type")

    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "Header{" +
                "contentType='" + contentType + '\'' +
                '}';
    }
}
