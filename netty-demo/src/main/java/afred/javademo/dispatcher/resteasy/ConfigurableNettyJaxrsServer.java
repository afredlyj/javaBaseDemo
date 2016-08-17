package afred.javademo.dispatcher.resteasy;

import com.google.common.collect.Lists;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.resteasy.plugins.server.netty.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

import static org.jboss.resteasy.plugins.server.netty.RestEasyHttpRequestDecoder.Protocol.HTTP;

/**
 * Created by afred on 16/8/16.
 */
public class ConfigurableNettyJaxrsServer extends NettyJaxrsServer {

    private int backlog = 128;
    private int maxRequestSize = 1024 * 1024 * 10;
    private int maxInitialLineLength = 4096;
    private int maxHeaderSize = 8192;
    private int maxChunkSize = 8192;

    private static final Logger logger = LoggerFactory.getLogger(ConfigurableNettyJaxrsServer.class);

    private NioEventLoopGroup group;

    private int ioThreads;


    private List<ChannelHandler> channelHandlers = Lists.newLinkedList();
    private List<ChannelHandler> httpChannelHandlers = Lists.newLinkedList();

    public ConfigurableNettyJaxrsServer(int ioThreads) {

        this.ioThreads = ioThreads;
    }

    @Override
    public void start() {

        group = new NioEventLoopGroup(this.ioThreads);
        deployment.start();
        // Configure the server.
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(createChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, backlog)
                .childOption(ChannelOption.SO_KEEPALIVE, true);


        final InetSocketAddress socketAddress;
        if (null == hostname || hostname.isEmpty()) {
            socketAddress = new InetSocketAddress(configuredPort);
        } else {
            socketAddress = new InetSocketAddress(hostname, configuredPort);
        }

        Channel channel = bootstrap.bind(socketAddress).syncUninterruptibly().channel();
        runtimePort = ((InetSocketAddress) channel.localAddress()).getPort();

    }

    private ChannelInitializer<SocketChannel> createChannelInitializer() {
        final RequestDispatcher dispatcher = createRequestDispatcher();
            return new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    setupHandlers(ch, dispatcher, HTTP);
                }
            };
    }

    private void setupHandlers(SocketChannel ch, RequestDispatcher dispatcher, RestEasyHttpRequestDecoder.Protocol protocol) {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(channelHandlers.toArray(new ChannelHandler[channelHandlers.size()]));
        channelPipeline.addLast(new HttpRequestDecoder(maxInitialLineLength, maxHeaderSize, maxChunkSize));
        channelPipeline.addLast(new HttpObjectAggregator(maxRequestSize));
        channelPipeline.addLast(new HttpResponseEncoder());
        channelPipeline.addLast(httpChannelHandlers.toArray(new ChannelHandler[httpChannelHandlers.size()]));
        channelPipeline.addLast(new RestEasyHttpRequestDecoder(dispatcher.getDispatcher(), root, protocol));
        channelPipeline.addLast(new RestEasyHttpResponseEncoder());
        channelPipeline.addLast(new RequestHandler(dispatcher));
    }

    @Override
    public void stop() {
        runtimePort = -1;
        group.shutdownGracefully();
    }
}
