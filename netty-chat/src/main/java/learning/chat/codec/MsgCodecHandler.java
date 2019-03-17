package learning.chat.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import learning.chat.protocol.Msg;

import java.util.List;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
@ChannelHandler.Sharable
public class MsgCodecHandler extends MessageToMessageCodec<ByteBuf, Msg> {
    public static final MsgCodecHandler INSTANCE = new MsgCodecHandler();

    private MsgCodecHandler() {
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Msg baseMsg, List<Object> out) throws Exception {
        ByteBuf byteBuf = channelHandlerContext.channel().alloc().ioBuffer();
        MsgCodec.encode(byteBuf, baseMsg);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        out.add(MsgCodec.decode(byteBuf));
    }
}
