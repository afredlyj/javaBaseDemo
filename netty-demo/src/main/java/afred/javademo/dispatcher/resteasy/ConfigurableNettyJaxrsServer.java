package afred.javademo.dispatcher.resteasy;

import com.google.common.collect.Lists;

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.plugins.server.netty.RequestDispatcher;
import org.jboss.resteasy.plugins.server.netty.RestEasyHttpRequestDecoder;
import org.jboss.resteasy.plugins.server.netty.RestEasyHttpResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

import afred.javademo.dispatcher.resteasy.nettyhandler.NettyInboundHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

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
                .handler(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                        logger.debug("boss channel register : {}", ctx);
                        super.channelRegistered(ctx);
                    }

                    @Override
                    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                        logger.debug("boss channel channelUnregistered : {}", ctx);

                        super.channelUnregistered(ctx);
                    }

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        logger.debug("boss channel channelActive : {}", ctx);

                        super.channelActive(ctx);
                    }

                    @Override
                    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                        logger.debug("boss channel channelInactive : {}", ctx);

                        super.channelInactive(ctx);
                    }

                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        super.channelRead(ctx, msg);
                    }

                    @Override
                    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                        logger.debug("boss channel channelReadComplete : {}", ctx.channel());
                        super.channelReadComplete(ctx);
                    }

                    @Override
                    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                        super.userEventTriggered(ctx, evt);
                    }

                    @Override
                    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
                        logger.debug("boss channel channelWritabilityChanged : {}", ctx);

                        super.channelWritabilityChanged(ctx);
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        super.exceptionCaught(ctx, cause);
                    }
                })
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
        channelPipeline.addLast(new ChannelInboundHandlerAdapter(){
            @Override
            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                logger.debug("child channelRegistered : {}, {}", ctx.channel(), ctx.channel().parent());
                super.channelRegistered(ctx);
            }

            @Override
            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                super.channelUnregistered(ctx);
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                super.channelActive(ctx);
            }

            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                super.channelInactive(ctx);
            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                super.channelRead(ctx, msg);
            }

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                super.channelReadComplete(ctx);
            }

            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                super.userEventTriggered(ctx, evt);
            }

            @Override
            public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
                super.channelWritabilityChanged(ctx);
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                super.exceptionCaught(ctx, cause);
            }
        });
        channelPipeline.addLast(new HttpRequestDecoder(maxInitialLineLength, maxHeaderSize, maxChunkSize));
        channelPipeline.addLast(new HttpObjectAggregator(maxRequestSize));
        channelPipeline.addLast(new HttpResponseEncoder());
        channelPipeline.addLast(httpChannelHandlers.toArray(new ChannelHandler[httpChannelHandlers.size()]));
        channelPipeline.addLast(new RestEasyHttpRequestDecoder(dispatcher.getDispatcher(), root, protocol));
        channelPipeline.addLast(new RestEasyHttpResponseEncoder());
        channelPipeline.addLast(new NettyInboundHandler(dispatcher));
    }

    @Override
    public void stop() {
        runtimePort = -1;
        group.shutdownGracefully();
    }
}
