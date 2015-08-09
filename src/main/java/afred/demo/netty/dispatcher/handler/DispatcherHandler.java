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
         * ����Ǽ򵥵��߼����������Բ���ҪEventExecutorGroup�������������л�
         */
        // todo �ĳ�Spring����������
        if ("/SimpleRequest".equals(msg.getUri())) {
            ctx.pipeline().addLast("simpleHttpHandler", new HttpServerHandler());

        } else {
            ctx.pipeline().addLast(eventExecutorGroup, "groupHttpHandler", new HttpServerHandler());
        }

        // ��֤��handlerֻ���ڵ�һ�ε���ʱִ��
        ctx.pipeline().remove(this);
        logger.debug("handler names : {}", ctx.pipeline().names());

        /**
         * SimpleChannelInboundHandler ���Զ�releaseһ�Σ�����Ϊ�˷�ֹ
         * io.netty.util.IllegalReferenceCountException: refCnt: 0, decrement: 1��
         * ������Ҫ�ֶ�����һ��msg���á�
         */
        ReferenceCountUtil.retain(msg);
        ctx.fireChannelRead(msg);

    }
}
