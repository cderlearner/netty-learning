package learning.chat.protocol.s2c;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
@Data
public class SCLoginMsg extends Msg {
    private String userId;
    private String userName;
    private boolean success;
    private String result;

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.SC_LOGIN;
    }
}
