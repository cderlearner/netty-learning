package learning.chat.protocol.c2s;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

/**
 * Author: linjx
 * Date: 2019/3/12
 */
@Data
public class CSLogoutMsg extends Msg {

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.CS_LOGOUT;
    }
}
