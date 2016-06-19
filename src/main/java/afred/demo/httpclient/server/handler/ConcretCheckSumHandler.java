package afred.demo.httpclient.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Afred on 14-11-25.
 */
public class ConcretCheckSumHandler extends GenericCheckSumHandler<FullHttpRequest> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


}
