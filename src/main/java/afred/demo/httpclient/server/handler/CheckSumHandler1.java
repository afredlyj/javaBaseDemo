package afred.demo.httpclient.server.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by Afred on 14-11-25.
 */
public class CheckSumHandler1 extends MessageToMessageDecoder<FullHttpRequest> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean checkSum(FullHttpRequest msg) throws Exception {

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

        if (org.apache.commons.lang.StringUtils.isEmpty(userAgent)) {
            logger.info("check sum fail");
            return false;
        }

        String localMd5 = DigestUtils.md5Hex(userAgent);

        logger.info("check sum fail : {}, {}", localMd5, checkSum);

        return localMd5.equals(checkSum);

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpRequest msg, List<Object> out) throws Exception {

        logger.debug("decode request");

        if (!msg.getDecoderResult().isSuccess()) {
            logger.warn("decode failure");
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }

        // check sum
        if (!checkSum(msg)) {
            logger.warn("check sum failure");
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }

        /**
         *  Be aware that you need to call {@link io.netty.util.ReferenceCounted#retain()} on messages that are just passed through if they
         * are of type {@link io.netty.util.ReferenceCounted}. This is needed as the {@link MessageToMessageDecoder} will call
         * {@link io.netty.util.ReferenceCounted#release()} on decoded messages.
         */
        ReferenceCountUtil.retain(msg);
        logger.debug("decode success");
        out.add(msg);
    }


    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
                Unpooled.copiedBuffer("Failure : " + status.toString(), CharsetUtil.UTF_8));

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
