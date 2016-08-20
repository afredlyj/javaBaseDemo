package afred.javademo.dispatcher.resteasy.nettyhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;
import org.jboss.resteasy.plugins.server.netty.NettyHttpResponse;
import org.jboss.resteasy.plugins.server.netty.RequestDispatcher;
import org.jboss.resteasy.plugins.server.netty.i18n.LogMessages;
import org.jboss.resteasy.plugins.server.netty.i18n.Messages;
import org.jboss.resteasy.spi.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by afred on 16/8/18.
 */
public class NettyInboundHandler extends SimpleChannelInboundHandler<NettyHttpRequest> {

    private static final Logger logger = LoggerFactory.getLogger(NettyInboundHandler.class);

    private static Executor DEFAULT_EXECUTOR = Executors.newFixedThreadPool(8);

    private Executor executor;

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    protected final RequestDispatcher dispatcher;

    public NettyInboundHandler(RequestDispatcher dispatcher) {
        this(dispatcher, DEFAULT_EXECUTOR);
    }


    public NettyInboundHandler(RequestDispatcher dispatcher, Executor executor) {
        this.dispatcher = dispatcher;
        this.executor = executor;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyHttpRequest request) throws Exception {

        logger.debug("url : {}", request.getUri().getPath());

        if (executor != null) {
            executor.execute(new NettyBizTask(ctx, request, dispatcher));
        }  else {
            new NettyBizTask(ctx, request, dispatcher).run();
        }
    }

    static class NettyBizTask implements Runnable {

        private final ChannelHandlerContext context;

        private final NettyHttpRequest request;

        private final RequestDispatcher dispatcher;

        public NettyBizTask(ChannelHandlerContext context, NettyHttpRequest request, RequestDispatcher dispatcher) {
            this.context = context;
            this.request = request;
            this.dispatcher = dispatcher;
        }

        @Override
        public void run() {

            try {
                NettyHttpResponse response = request.getResponse();
                try
                {
                    dispatcher.service(context, request, response, true);
                }
                catch (Failure e1)
                {
                    response.reset();
                    response.setStatus(e1.getErrorCode());
                }
                catch (Exception ex)
                {
                    response.reset();
                    response.setStatus(500);
                    LogMessages.LOGGER.error(Messages.MESSAGES.unexpected(), ex);
                }

                if (!request.getAsyncContext().isSuspended()) {
                    response.finish();
                }
            } catch (Exception e) {
                logger.error("exception", e);
            }
        }
    }
}
