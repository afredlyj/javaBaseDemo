package afred.javademo.dispatcher.client;

import afred.common.netty.util.NettyUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

/**
 * Created by winnie on 2016-03-27 .
 */
public class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientHandler.class);

    private Object lock = new Object();

    private volatile String result;

    private boolean received = false;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {

        byte[] bs = NettyUtil.getBytes(msg.content());

        String content = new String(bs, StandardCharsets.UTF_8);
        logger.debug("receive server response : {}", content);


        synchronized (lock) {

            result = content;
            received = true;
            lock.notifyAll();
        }

    }

    public String getResult() {

        synchronized (lock) {
            while (!received) {

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    logger.error("exception ", e);
                }
            }

            return result;
        }

    }
}
