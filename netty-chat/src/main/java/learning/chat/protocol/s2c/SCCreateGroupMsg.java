package learning.chat.protocol.s2c;

import learning.chat.protocol.Msg;
import learning.chat.protocol.MsgTypeConfig;
import lombok.Data;

import java.util.List;

/**
 * Author: linjx
 * Date: 2019/3/12
 */
@Data
public class SCCreateGroupMsg extends Msg {
    private String groupId;
    private List<String> userNameList;
    private boolean success;

    @Override
    public Byte getMsgType() {
        return MsgTypeConfig.SC_CREATE_GROUP;
    }
}
