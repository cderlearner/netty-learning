package learning.chat.protocol.c2s;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

@Data
public class CSGroupChatMsg extends Msg {
    private String toGroupId;
    private String message;

    public CSGroupChatMsg(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.CS_GROUP_CHAT;
    }
}
