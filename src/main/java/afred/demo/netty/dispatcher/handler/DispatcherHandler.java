package afred.demo.netty.dispatcher.handler;

import afred.demo.netty.dispatcher.data.HttpBodyHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by winnie on 2015-08-09 .
 */
public class DispatcherHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherHandler.class);

    private EventExecutorGroup eventExecutorGroup;

    public DispatcherHandler(EventExecutorGroup eventExecutorGroup) {
        if (null == eventExecutorGroup) {
            throw new NullPointerException();
        }

        this.eventExecutorGroup = eventExecutorGroup;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

        logger.debug("request uri : {}", msg.getUri());

//        HttpBodyHolder holder = new HttpBodyHolder(msg);

        /**
         * channel和pipeline绑定，如果是同一个channel，在第一次请求之后，pipeline的handler就固定了。
         * 这个方案是错误的。
         */
        if ("/SimpleRequest".equals(msg.getUri())) {
            ctx.pipeline().addLast("simpleHttpHandler", new HttpServerHandler());
        } else {
            ctx.pipeline().addLast(eventExecutorGroup, "groupHttpHandler", new HttpServerHandler());
        }

        ctx.pipeline().remove(this);
        logger.debug("handler names : {}", ctx.pipeline().names());

        ReferenceCountUtil.retain(msg);
        ctx.fireChannelRead(msg);

    }
}
