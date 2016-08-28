package afred.javademo.httpclient.server.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by Afred on 14-11-25.
 */
public class RequestHeader implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RequestHeader.class);

    private String userAgent;

    private String clientIp;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    @Override
    public String toString() {
        return "RequestHeader{" +
                "userAgent='" + userAgent + '\'' +
                ", clientIp='" + clientIp + '\'' +
                '}';
    }
}
