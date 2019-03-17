package learning.chat.protocol.s2c;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

/**
 * Author: linjx
 * Date: 2019/3/12
 */
@Data
public class SCLogoutMsg extends Msg {
    private boolean success;
    private String reason;

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.SC_LOGOUT;
    }
}
