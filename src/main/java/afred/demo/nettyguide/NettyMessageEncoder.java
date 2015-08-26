package afred.demo.nettyguide;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Afred on 15/7/25.
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    private static final Logger logger = LoggerFactory.getLogger(NettyMessageEncoder.class);

    protected MarshallerFactory createMarshallerFactory() {
        return Marshalling.getProvidedMarshallerFactory("serial");
    }

    protected MarshallingConfiguration createMarshallingConfig() {
        // Create a configuration
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return configuration;
    }

    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        this.marshallingEncoder = new MarshallingEncoder(new DefaultMarshallerProvider(createMarshallerFactory(), createMarshallingConfig()));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        if (msg == null || msg.getHeader() == null) {
            throw new Exception("illegal request argument");
        }

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(msg.getHeader().getCrcCode());
        buf.writeInt(msg.getHeader().getLength());
        buf.writeLong(msg.getHeader().getSessionID());
        buf.writeByte(msg.getHeader().getType());
        buf.writeByte(msg.getHeader().getPriority());
        buf.writeInt(msg.getHeader().getAttachment().size());

        String key = null;
        byte[] keyArray = null;
        Object value = null;

        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            buf.writeInt(keyArray.length);
            buf.writeBytes(keyArray);
            value = param.getValue();
//            marshallingEncoder.
        }

    }
}
