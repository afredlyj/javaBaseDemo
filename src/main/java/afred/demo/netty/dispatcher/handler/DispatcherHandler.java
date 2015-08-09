package afred.demo.netty.dispatcher.handler;

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

        /**
         * 如果是简单的逻辑操作，可以不需要EventExecutorGroup，减少上下文切换
         */
        // todo 改成Spring，并可配置
        if ("/SimpleRequest".equals(msg.getUri())) {
            ctx.pipeline().addLast("simpleHttpHandler", new HttpServerHandler());

        } else {
            ctx.pipeline().addLast(eventExecutorGroup, "groupHttpHandler", new HttpServerHandler());
        }

        // 保证该handler只有在第一次调用时执行
        ctx.pipeline().remove(this);
        logger.debug("handler names : {}", ctx.pipeline().names());

        /**
         * SimpleChannelInboundHandler 会自动release一次，所以为了防止
         * io.netty.util.IllegalReferenceCountException: refCnt: 0, decrement: 1，
         * 这里需要手动增加一次msg引用。
         */
        ReferenceCountUtil.retain(msg);
        ctx.fireChannelRead(msg);

    }
}
