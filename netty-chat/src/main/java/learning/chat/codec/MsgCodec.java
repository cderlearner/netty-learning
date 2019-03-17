package learning.chat.codec;

import io.netty.buffer.ByteBuf;
import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import learning.chat.protocol.MsgSerializer;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
public interface MsgCodec {

    static void encode(ByteBuf byteBuf, Msg baseMsg) {
        byte[] bytes = MsgSerializer.DEFAULT.toBytes(baseMsg);

        byteBuf.writeInt(bytes.length + 1);
        byteBuf.writeByte(baseMsg.getMsgType());
        byteBuf.writeBytes(bytes);
    }

    static Msg decode(ByteBuf byteBuf) {
        // 数据包长度
        int length = byteBuf.readInt();
        // 消息号
        byte msgType = byteBuf.readByte();
        int bodyLength = length - 1;
        // 消息内容
        byte[] bytes = new byte[bodyLength];
        byteBuf.readBytes(bytes);

        Class<? extends Msg> msgClass = MsgTypeConfig.fetchMsgClass(msgType);
        if (msgClass != null) {
            return MsgSerializer.DEFAULT.fromBytes(msgClass, bytes);
        }
        return null;
    }
}
