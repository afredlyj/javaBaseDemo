package afred.javademo.httpclient.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created by Afred on 14-11-25.
 */
public class ConcretCheckSumHandler extends GenericCheckSumHandler<FullHttpRequest> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


}
