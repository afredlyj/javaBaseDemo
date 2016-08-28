package afred.javademo.httpclient.server.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import afred.javademo.httpclient.server.handler.ConcretCheckSumHandler;
import afred.javademo.httpclient.server.handler.HttpServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by winnie on 2014-11-09 .
 */
public class HttpServerInitializer extends ChannelInitializer<NioSocketChannel> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private EventExecutorGroup bzGroup;

    public HttpServerInitializer() {
        this(null);
    }

    public HttpServerInitializer(EventExecutorGroup bzGroup) {
        this.bzGroup = bzGroup;
    }

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {

        logger.debug("init channel");

//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("encoder", new HttpResponseEncoder());
        /**
         * Be aware that you need to have the HttpResponseEncoder or HttpRequestEncoder
         * before the HttpObjectAggregator in the ChannelPipeline.
         */
        pipeline.addLast("aggregator", new HttpObjectAggregator(1048576));
//        pipeline.addLast("checkSum", new CheckSumHandler());
        pipeline.addLast("checkSum", new ConcretCheckSumHandler());
        if (bzGroup != null) {
            pipeline.addLast(bzGroup, "serverHandler", new HttpServerHandler());
        } else {
            pipeline.addLast("serverHandler", new HttpServerHandler());
        }
    }
}
