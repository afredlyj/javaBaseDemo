package afred.javademo.dispatcher.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

import afred.common.netty.data.HttpBodyHolder;
import afred.common.netty.util.NettyUtil;
import io.netty.channel.ChannelHandlerContext;

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
        byte[] responseData = "ddddfadsfasfsadfasdfdsafsadfsafewfasdcadscecascaescasascasdcasdcasdcsadcascaseacdacacdscacsacascsasa".getBytes(StandardCharsets.UTF_8);
        NettyUtil.response(ctx, holder, responseData);

    }
}
