package afred.javademo.dispatcher.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;


/**
 * Created by winnie on 2016-03-27 .
 */
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    private static void run(String host, int port) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();

        final HttpClientHandler httpClientHandler = new HttpClientHandler();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();

                        pipeline.addLast("decoder", new HttpResponseDecoder());
                        pipeline.addLast("encoder", new HttpRequestEncoder());

                        /**
                         * Be aware that you need to have the HttpResponseEncoder or HttpRequestEncoder
                         * before the HttpObjectAggregator in the ChannelPipeline.
                         */
                        pipeline.addLast("aggregator", new HttpObjectAggregator(1048576));
                        pipeline.addLast("dispatcher", httpClientHandler);

                        logger.debug("pipeline 初始化 : {}", pipeline.names());

                    }
                });


        ChannelFuture future = b.connect(host, port).awaitUninterruptibly();
        Channel ch = future.channel();

        ByteBuf buf = Unpooled.wrappedBuffer("hello, server".getBytes(StandardCharsets.UTF_8));
        HttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, HttpMethod.GET, "/hello", buf);
        request.headers().set(HttpHeaders.Names.HOST, host);
        request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
        request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, buf.readableBytes());

        // Send the HTTP request.
        ch.writeAndFlush(request);

        // get response from server


        ch.closeFuture().sync();


        logger.debug("response : {}", httpClientHandler.getResult());

    }

    public static void main(String[] args) throws InterruptedException {
        run("127.0.0.1", 8080);
    }
}
