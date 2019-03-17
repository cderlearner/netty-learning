package learning.chat.protocol.c2s;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

/**
 * 单聊请求消息
 * Author: linjx
 * Date: 2019/3/12
 */
@Data
public class CSSingleChatMsg extends Msg {
    private String toUserId;
    private String message;

    public CSSingleChatMsg(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.CS_SINGLE_CHAT;
    }
}
