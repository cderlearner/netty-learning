package learning.chat.protocol.s2c;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import learning.chat.session.Session;
import lombok.Data;

@Data
public class SCGroupChatMsg extends Msg {
    private String fromGroupId;
    private Session fromUser;
    private String message;

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.SC_GROUP_CHAT;
    }
}
