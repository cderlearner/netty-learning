package learning.chat.protocol.s2c;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

@Data
public class SCQuitGroupMsg extends Msg {
    private String groupId;
    private boolean success;
    private String reason;

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.SC_QUIT_GROUP;
    }
}
