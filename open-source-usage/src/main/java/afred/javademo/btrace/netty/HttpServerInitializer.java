package afred.javademo.btrace.netty;

import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.EventExecutorGroup;


/**
 * Created by winnie on 2016-01-16 .
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    private static final Logger logger = LoggerFactory.getLogger(HttpServerInitializer.class);

    private EventExecutorGroup group;

    private ChannelHandler dispatcherHandler;

    public HttpServerInitializer(EventExecutorGroup group, ChannelHandler dispatcherHandler) {

        this.group = group;

        this.dispatcherHandler = Preconditions.checkNotNull(dispatcherHandler);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {


        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("encoder", new HttpResponseEncoder());

        pipeline.addLast("idle", new IdleStateHandler(30, 30, 30));

        /**
         * Be aware that you need to have the HttpResponseEncoder or HttpRequestEncoder
         * before the HttpObjectAggregator in the ChannelPipeline.
         */
        pipeline.addLast("aggregator", new HttpObjectAggregator(1048576));

        if (this.group != null) {
            pipeline.addLast(this.group, dispatcherHandler);
        } else {
            pipeline.addLast(dispatcherHandler);
        }


    }
}
