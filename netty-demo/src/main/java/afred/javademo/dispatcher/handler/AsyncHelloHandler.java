package afred.javademo.dispatcher.handler;

import afred.common.netty.data.HttpBodyHolder;
import afred.common.netty.util.NettyUtil;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by winnie on 2016-01-17 .
 */
public class AsyncHelloHandler implements IHandler {

    private static final Logger logger = LoggerFactory.getLogger(AsyncHelloHandler.class);

    public boolean async() {
        return true;
    }

    public void handleRequest(ChannelHandlerContext ctx, HttpBodyHolder holder) {

        logger.info("async hello handler");
        byte[] bytes = holder.content();
        NettyUtil.response(ctx, holder, bytes);

    }
}
