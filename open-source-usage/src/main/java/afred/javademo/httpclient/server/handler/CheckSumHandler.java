package afred.javademo.httpclient.server.handler;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;

/**
 * Created by Afred on 14-11-25.
 */
public class CheckSumHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

        logger.debug("enter check sum handler ");

        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(msg.getUri());

        logger.debug("uri path : {}", queryStringDecoder.path());

        logger.debug("query string : {}", queryStringDecoder.parameters());

        for (Map.Entry<String, String> header : msg.headers()) {
            logger.debug("key : {}, value : {}", header.getKey(), header.getValue());
        }

        HttpHeaders headers = msg.headers();
        String userAgent = headers.get("ua");
        String checkSum = headers.get("checkSum");

        DefaultHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
        HttpHeaders.setContentLength(response, 0);

        if (org.apache.commons.lang.StringUtils.isEmpty(userAgent)) {
            logger.info("check sum fail");
            ctx.writeAndFlush(response);
            return;
        }

        String localMd5 = DigestUtils.md5Hex(userAgent);

        if (!localMd5.equals(checkSum)) {
            logger.info("check sum fail : {}, {}", localMd5, checkSum);
            ctx.writeAndFlush(response);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    public static void main(String[] args) {
//        String data = "123456";
//        System.out.println(StringUtils.getBytesUtf8(data));
//        System.out.println(DigestUtils.md5(data));
//
//        data = DigestUtils.md5Hex(data);
//        System.out.println(data);
//
//        Map<String, String> map = Maps.newHashMap();
//
//        System.out.println(map.getClass().getSuperclass());
//
//        System.out.println(map.getClass().getGenericSuperclass());
//
//        TypeVariable<? extends Class<? extends Map>>[] typeParameters = map.getClass().getTypeParameters();
//        for (TypeVariable<? extends Class<? extends Map>> type : typeParameters) {
//            System.out.println(type.getName());
//        }

        GenericCheckSumHandler genericCheckSumHandler = new GenericCheckSumHandler();
    }
}
