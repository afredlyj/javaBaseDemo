package afred.common.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by afred on 16/11/12.
 * 该handler放置在http协议解析之前,所以channelRead方法可能会被多次调用
 * startTime需要简单判断
 */
public class HttpLoggingHandler extends LoggingHandler {

    private static final Logger logger = LoggerFactory.getLogger(HttpLoggingHandler.class);

    private long startTime = 0;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (startTime <= 0) {
            startTime = System.nanoTime();
        }

        super.channelRead(ctx, msg);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {

        long totalTime = System.nanoTime() - startTime;
        startTime = 0;

        logger.info("channel({}) 处理耗时 : {}", ctx.channel(), totalTime);

        super.flush(ctx);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
    }
}
