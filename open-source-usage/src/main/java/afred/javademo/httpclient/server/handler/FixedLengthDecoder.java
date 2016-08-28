package afred.javademo.httpclient.server.handler;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by Afred on 15/7/5.
 */
public class FixedLengthDecoder extends ByteToMessageDecoder {

    private final int frameLength;


    public FixedLengthDecoder(int frameLength) {
        if (frameLength <= 0) {
            throw new IllegalArgumentException("frame length must be > 0 : " + frameLength);
        }

        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {

        while (in.readableBytes() >= frameLength) {
            ByteBuf buf = in.readBytes(frameLength);
            out.add(buf);
        }

        ReferenceCountUtil.retain(in);

    }
}
