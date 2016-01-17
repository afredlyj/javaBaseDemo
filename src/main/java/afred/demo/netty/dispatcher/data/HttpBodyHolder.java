package afred.demo.netty.dispatcher.data;

import afred.demo.netty.dispatcher.util.NettyUtil;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;

import java.io.Serializable;

/**
 * Created by winnie on 2016-01-17 .
 */
public class HttpBodyHolder implements Serializable {

    private final String uri;

    private final byte[] content;

    private final HttpHeaders headers;

    private final HttpMessage message;

    private long startTime;

    public HttpBodyHolder(FullHttpRequest request) {
        this.uri = request.getUri();
        this.message = request;
        this.content = NettyUtil.getBytes(request.content());
        this.headers = request.headers();
        startTime = System.currentTimeMillis();
    }

    public boolean isKeepAlive() {
        return HttpHeaders.isKeepAlive(message);
    }

    public String getUri() {
        return uri;
    }

    public byte[] content() {
        return content;
    }

    public HttpHeaders headers() {
        return headers;
    }
}
