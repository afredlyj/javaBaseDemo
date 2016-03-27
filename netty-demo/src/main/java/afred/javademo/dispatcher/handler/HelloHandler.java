package afred.javademo.dispatcher.handler;

import afred.common.netty.data.HttpBodyHolder;
import afred.common.netty.util.NettyUtil;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * Created by winnie on 2016-01-17 .
 */
public class HelloHandler implements IHandler {

    private static final Logger logger = LoggerFactory.getLogger(HelloHandler.class);

    public boolean async() {
        return false;
    }

    public void handleRequest(ChannelHandlerContext ctx, HttpBodyHolder holder) {

        logger.info("hello handler");
        byte[] bytes = holder.content();

        logger.debug("request data : {}", new String(bytes, StandardCharsets.UTF_8));
        NettyUtil.response(ctx, holder, bytes);

    }
}
