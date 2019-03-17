package learning.chat.protocol.s2c;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: linjx
 * Date: 2019/3/12
 */
@Data
@NoArgsConstructor
public class SCSingleChatMsg extends Msg {
    private String fromUserId;
    private String fromUserName;
    private String message;

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.SC_SINGLE_CHAT;
    }
}
