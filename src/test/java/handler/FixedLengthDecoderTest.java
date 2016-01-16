package handler;

import afred.demo.httpclient.server.handler.FixedLengthDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Afred on 15/7/5.
 */
public class FixedLengthDecoderTest {

    @Test
    public void testFrameDecoder() {
        ByteBuf buf = Unpooled.buffer();

        for (int i = 0; i < 100; i++) {
            buf.writeByte(i);
        }

        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthDecoder(10));

        Assert.assertTrue(channel.writeInbound(input));

        Assert.assertTrue(channel.finish());

//        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(buf.readBytes(10), channel.readInbound());
//        }

//        Assert.assertNull(channel.readInbound());
    }


}
