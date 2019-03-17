package learning.chat.protocol.c2s;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

import java.util.List;

/**
 * Author: linjx
 * Date: 2019/3/12
 */
@Data
public class CSCreateGroupMsg extends Msg {

    private List<String> userIdList;

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.CS_CREATE_GROUP;
    }
}
