package afred.demo.btrace.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by winnie on 2016-01-16 .
 */
public class NettyMain {

    //

    public static void main(String[] args) throws InterruptedException {

        start(8080);
    }

    private static void start(int port) throws InterruptedException {

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        HttpDispatcherHandler handler = new HttpDispatcherHandler();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer(null, handler));

        ChannelFuture future = bootstrap.bind(port).sync();

        future.channel().closeFuture().sync();

    }


}
