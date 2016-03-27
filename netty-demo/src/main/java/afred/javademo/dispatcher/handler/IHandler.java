package afred.javademo.dispatcher.handler;

import afred.common.netty.data.HttpBodyHolder;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by winnie on 2016-01-17 .
 */
public interface IHandler {

    public boolean async();

    public void handleRequest(ChannelHandlerContext ctx, HttpBodyHolder holder);

}
