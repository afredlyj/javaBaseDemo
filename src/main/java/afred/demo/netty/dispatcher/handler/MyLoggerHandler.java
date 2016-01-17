package afred.demo.netty.dispatcher.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by winnie on 2016-01-17 .
 */
public class MyLoggerHandler extends LoggingHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyLoggerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        FullHttpRequest request = (FullHttpRequest) msg;

        logger.debug("url : {}", request.getUri());
        logger.debug("pipeline : {}", ctx.pipeline().names());

        super.channelRead(ctx, msg);
    }
}
