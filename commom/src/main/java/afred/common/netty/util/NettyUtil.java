package afred.common.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by winnie on 2016-01-17 .
 */
public class NettyUtil {

    private static final Logger logger = LoggerFactory.getLogger(NettyUtil.class);
    private static final String HELLO = "hello";

    public static byte[] getBytes(ByteBuf byteBuf) {

        byte[] content = null;
        int length = byteBuf.readableBytes();
        if (byteBuf.hasArray()) {
            content = byteBuf.array();
        } else {
            content = new byte[length];
            getBytes(byteBuf, content);
        }


        return content;
    }

    private static ByteBuf getBytes(ByteBuf byteBuf, byte[] content) {
        return byteBuf.getBytes(byteBuf.readerIndex(), content);
    }


    public static DefaultFullHttpResponse createHttpResponse(HttpVersion version, HttpResponseStatus status, ByteBuf byteBuf) {
        return new DefaultFullHttpResponse(version, status, byteBuf);
    }

//    public static void response(ChannelHandlerContext ctx, HttpBodyHolder holder, byte[] bytes) {
//
//        logger.info("耗时 : {}", (System.currentTimeMillis() - holder.getStartTime()));
//
//        ByteBuf byteBuf = ctx.alloc().buffer();
//        DefaultFullHttpResponse response = NettyUtil.createHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
//        response.content().writeBytes(bytes);
//        response.headers().set("Content-type", "text/plain;charset=UTF-8");
//
//        boolean keepAlive = holder.isKeepAlive();
//        HttpHeaders.setKeepAlive(response, keepAlive);
//        int len = response.content().readableBytes();
//
//        logger.debug("response length : {}", len);
//
//
//
//        HttpHeaders.setContentLength(response, len);
//
//        if (!keepAlive) {
//            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
//        } else {
//
//            ctx.writeAndFlush(response);
//        }
//    }

    public static void response(ChannelHandlerContext ctx, boolean keepAlive, byte[] bytes) {


        ByteBuf byteBuf = ctx.alloc().buffer();
        DefaultFullHttpResponse response = NettyUtil.createHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        response.content().writeBytes(bytes);
        response.headers().set("Content-type", "text/plain;charset=UTF-8");

        HttpHeaders.setKeepAlive(response, keepAlive);
        int len = response.content().readableBytes();

        logger.debug("response length : {}", len);

        HttpHeaders.setContentLength(response, len);

        if (!keepAlive) {
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {

            ctx.writeAndFlush(response);
        }
    }

    public static void main(String[] args) {
        System.out.println(HELLO);
    }
}
