package afred.common.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.util.concurrent.EventExecutorGroup;
/**
 * Created by winnie on 2014-11-09 .
 */
public class HttpServerInitializer extends ChannelInitializer<NioSocketChannel> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private EventExecutorGroup group;

    private SimpleChannelInboundHandler<FullHttpRequest> httpDispatchHandler;

    public HttpServerInitializer(EventExecutorGroup group, SimpleChannelInboundHandler<FullHttpRequest> httpDispatchHandler) {
        this.group = group;
        this.httpDispatchHandler = httpDispatchHandler;
    }

    public HttpServerInitializer(EventExecutorGroup group) {
        this.group = group;
    }

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {


        ChannelPipeline pipeline = ch.pipeline();

//        pipeline.addLast("idle", new IdleChannelHandler(5, 5, 5));
        pipeline.addLast("logger", new HttpLoggingHandler());
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("encoder", new HttpResponseEncoder());

        /**
         * Be aware that you need to have the HttpResponseEncoder or HttpRequestEncoder
         * before the HttpObjectAggregator in the ChannelPipeline.
         */
        pipeline.addLast("aggregator", new HttpObjectAggregator(1048576));

        Sharable sharable = this.httpDispatchHandler.getClass().getAnnotation(Sharable.class);
        if (sharable != null) {
            pipeline.addLast("dispatcher", this.httpDispatchHandler);
        } else {
            logger.error("不能被多次添加");
        }



        logger.debug("pipeline 初始化 : {}", pipeline.names());
    }
}
