package afred.demo.httpclient.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Afred on 14-11-25.
 */
public class GenericCheckSumHandler<T extends HttpRequest> extends SimpleChannelInboundHandler<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {

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
}
