package afred.demo.netty.dispatcher.handler;

import afred.demo.netty.dispatcher.data.HttpBodyHolder;
import afred.demo.netty.dispatcher.util.NettyUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * Created by winnie on 2014-11-09 .
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

        logger.debug("receive data : {}", request.getClass());

//        HttpHeaders headers = request.headers();
//        for (String name : headers.names()) {
//            String value = headers.get(name);
//            logger.debug("request header : {}, {}", name, value);
//        }

        QueryStringDecoder queryString = new QueryStringDecoder(request.getUri());
        logger.info("query String : {}", queryString.parameters());


        response(ctx, request, "hello world".getBytes(StandardCharsets.UTF_8));
    }

    private void response(ChannelHandlerContext ctx, FullHttpRequest request, byte[] bytes) {

        ByteBuf byteBuf = ctx.alloc().buffer();
        DefaultFullHttpResponse response = NettyUtil.createHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        response.content().writeBytes(bytes);
        response.headers().set("Content-type", "text/plain;charset=UTF-8");

        boolean keepAlive = HttpHeaders.isKeepAlive(request);
        HttpHeaders.setKeepAlive(response, keepAlive);
        int len = response.content().readableBytes();
        logger.debug("response length : {}", len);
        HttpHeaders.setContentLength(response, len);

        if (!keepAlive) {
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {

            ctx.write(response);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("flush");
        ctx.flush();
    }
}
