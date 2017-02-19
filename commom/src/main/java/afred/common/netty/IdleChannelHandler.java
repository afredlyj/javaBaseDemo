package afred.common.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by afred on 17/1/19.
 */
public class IdleChannelHandler extends IdleStateHandler {

    private static final Logger logger = LoggerFactory.getLogger(IdleChannelHandler.class);

    public IdleChannelHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {

        logger.debug("channel idle, close channel : {}, {}", ctx, evt);

        ctx.channel().close();

    }
}
