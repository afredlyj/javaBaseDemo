package afred.javademo.dispatcher.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import afred.common.netty.data.HttpBodyHolder;
import afred.common.netty.util.NettyUtil;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by winnie on 2016-01-17 .
 */
public class SleepHandler implements IHandler {

    private static final Logger logger = LoggerFactory.getLogger(SleepHandler.class);

    public boolean async() {
        return false;
    }

    public void handleRequest(ChannelHandlerContext ctx, HttpBodyHolder holder) {

        logger.info("hello handler");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {

        }

        byte[] bytes = "hello".getBytes(StandardCharsets.UTF_8);

        logger.debug("request data : {}", new String(bytes, StandardCharsets.UTF_8));
        NettyUtil.response(ctx, holder, bytes);

    }

    public static void main(String[] args) {
        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
        objectThreadLocal.set(new Object());
    }
}
