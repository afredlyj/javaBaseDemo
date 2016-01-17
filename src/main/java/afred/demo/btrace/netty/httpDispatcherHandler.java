package afred.demo.btrace.netty;

import afred.demo.btrace.Counter;
import com.google.common.base.Preconditions;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 2016-01-16 .
 */
@ChannelHandler.Sharable
public class HttpDispatcherHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger logger = LoggerFactory.getLogger(HttpDispatcherHandler.class);

    private ExecutorService executorService;

    private boolean async = false;

    public HttpDispatcherHandler() {
        async = false;
    }

    public HttpDispatcherHandler(ExecutorService executorService) {
        this.executorService = Preconditions.checkNotNull(executorService);
        async = true;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

        String uri = msg.getUri();

        run();

        logger.debug("request url : {}", uri);

        if (async) {

        } else {

            boolean keepAlive = HttpHeaders.isKeepAlive(msg);

            int sleepMils = RandomUtils.nextInt(10);
            logger.info("Synchronize handle : {}", sleepMils);

            TimeUnit.MILLISECONDS.sleep(sleepMils);
            logger.info("end sleep");
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8));
            logger.debug("keep alive : {}",keepAlive);
            logger.debug("protocol version : {}",msg.getProtocolVersion());

            response.headers().set("Content-type", "text/plain;charset=UTF-8");

            int len = response.content().readableBytes();
            logger.info("response content length : {}", len);
            HttpHeaders.setContentLength(response, len);

            logger.info("{}", response.getProtocolVersion().isKeepAliveDefault());
            HttpHeaders.setKeepAlive(response, keepAlive);
            ctx.writeAndFlush(response);
        }
    }

    private void run() {
        logger.info("run ... ");
    }
}
