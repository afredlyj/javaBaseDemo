package afred.demo.netty.dispatcher.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by winnie on 2014-11-09 .
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<Object> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        logger.debug("receive data : {}", msg.getClass());

        FullHttpRequest request = (FullHttpRequest) msg;
        HttpHeaders headers = request.headers();
        for (String name : headers.names()) {
            String value = headers.get(name);
            logger.debug("request header : {}, {}", name, value);
        }

        QueryStringDecoder queryString = new QueryStringDecoder(request.getUri());
        logger.info("query String : {}", queryString.parameters());

        ByteBuf content = request.content();

        int readableBytes = content.readableBytes();
        byte[] bs = null;
//        if (content.hasArray() && content.arrayOffset() == 0 && (readableBytes == content.capacity())) {
//            bs = content.array();
//        } else {
            bs = new byte[readableBytes];
            content.getBytes(0, bs, 0, readableBytes);
//        }

        String data = new String(bs);
        logger.info("request data : {}", data);

        boolean keepAlive = HttpHeaders.isKeepAlive(request);

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8));

        logger.debug("keep alive : {}",keepAlive);
        logger.debug("protocol version : {}",request.getProtocolVersion());

        response.headers().set("Content-type", "text/plain;charset=UTF-8");

        int len = response.content().readableBytes();
        logger.info("response content length : {}", len);
        HttpHeaders.setContentLength(response, len);

        logger.info("{}", response.getProtocolVersion().isKeepAliveDefault());
        HttpHeaders.setKeepAlive(response, keepAlive);

//        HttpHeaders responseHeaders = response.headers();
//        for (String name : responseHeaders.names()) {
//            String value = headers.get(name);
//            logger.debug("response header : {}, {}", name, value);
//        }

//        for (Map.Entry<String, String> e: responseHeaders) {
//            logger.debug("header : {}, {}", e.getKey(), e.getValue());
//        }

        logger.info("response : {}", response);

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
