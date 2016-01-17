package afred.demo.netty.dispatcher.util;

import afred.demo.netty.dispatcher.data.HttpBodyHolder;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.*;

/**
 * Created by winnie on 2016-01-17 .
 */
public class NettyUtil {

    public static byte[] getBytes(ByteBuf byteBuf) {

        byte[] content = null;
        int length = byteBuf.readableBytes();
        if (byteBuf.hasArray()) {
            content = byteBuf.array();
        } else {
            content = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), content);
        }

        return content;
    }


    public static DefaultFullHttpResponse createHttpResponse(HttpVersion version, HttpResponseStatus status, ByteBuf byteBuf) {
        return new DefaultFullHttpResponse(version, status, byteBuf);
    }
}
