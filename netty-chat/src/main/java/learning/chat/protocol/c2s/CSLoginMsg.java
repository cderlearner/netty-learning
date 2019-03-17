package learning.chat.protocol.c2s;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
@Data
public class CSLoginMsg extends Msg {
    private String userName;
    private String password;

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.CS_LOGIN;
    }
}
