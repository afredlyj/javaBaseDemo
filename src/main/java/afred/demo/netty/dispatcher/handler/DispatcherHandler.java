package afred.demo.netty.dispatcher.handler;

import afred.demo.netty.dispatcher.data.HttpBodyHolder;
import com.google.common.base.Preconditions;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 2015-08-09 .
 */
public class DispatcherHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherHandler.class);

    private ExecutorService executorService;

    private static ConcurrentMap<String, IHandler> map = new ConcurrentHashMap<String, IHandler>();

    public DispatcherHandler(ExecutorService executorService) {

        this.executorService = Preconditions.checkNotNull(executorService);

        map.putIfAbsent("/hello", new HelloHandler());
        map.putIfAbsent("/asyncHello", new AsyncHelloHandler());
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

        logger.debug("request uri : {}", msg.getUri());

        final HttpBodyHolder holder = new HttpBodyHolder(msg);

        /**
         * channel和pipeline绑定，如果是同一个channel，在第一次请求之后，pipeline的handler就固定了。
         * 这个方案是错误的。
         */
//        if ("/SimpleRequest".equals(msg.getUri())) {
//            ctx.pipeline().addLast("simpleHttpHandler", new HttpServerHandler());
//        } else {
//            ctx.pipeline().addLast(eventExecutorGroup, "groupHttpHandler", new HttpServerHandler());
//        }


        String uri = holder.getUri();
        final IHandler handler = map.get(uri);

        if (handler != null) {

            if (handler.async()) {
                executorService.execute(new Runnable() {
                    public void run() {
                        handler.handleRequest(ctx, holder);
                    }
                });
            } else {
                handler.handleRequest(ctx, holder);
            }
        }

    }
}
