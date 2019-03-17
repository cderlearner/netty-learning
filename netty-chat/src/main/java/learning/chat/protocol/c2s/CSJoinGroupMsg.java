package learning.chat.protocol.c2s;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

@Data
public class CSJoinGroupMsg extends Msg {
    private String groupId;

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.CS_JOIN_GROUP;
    }
}
