package learning.chat.protocol.s2c;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

@Data
public class SCJoinGroupMsg extends Msg {
    private String groupId;
    private String reason;
    private boolean success;

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.SC_JOIN_GROUP;
    }
}
