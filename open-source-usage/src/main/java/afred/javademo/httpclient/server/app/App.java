package afred.javademo.httpclient.server.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import afred.javademo.httpclient.server.init.HttpServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by winnie on 2014-11-09 .
 */
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        int port = 8080;

        new App().run(port);
    }

    private void run(int port) throws Exception {

        // configure server

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerInitializer());

            Channel channel = bootstrap.bind(port).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            logger.error("exception", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
