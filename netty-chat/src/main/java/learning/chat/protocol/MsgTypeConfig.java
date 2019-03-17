package learning.chat.protocol;

import learning.chat.protocol.c2s.*;
import learning.chat.protocol.s2c.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
public interface MsgTypeConfig {

    Byte CS_LOGIN = 1;
    Byte SC_LOGIN = 2;

    Byte CS_SINGLE_CHAT = 3;
    Byte SC_SINGLE_CHAT = 4;

    Byte CS_LOGOUT = 5;
    Byte SC_LOGOUT = 6;

    Byte CS_CREATE_GROUP = 7;
    Byte SC_CREATE_GROUP = 8;

    Byte CS_JOIN_GROUP = 11;
    Byte SC_JOIN_GROUP = 12;

    Byte CS_QUIT_GROUP = 13;
    Byte SC_QUIT_GROUP = 14;

    Byte CS_GROUP_CHAT = 15;
    Byte SC_GROUP_CHAT = 16;

    Map<Byte, Class<? extends Msg>> msgTypeMap = new HashMap<Byte, Class<? extends Msg>>(){{
        put(CS_LOGIN, CSLoginMsg.class);
        put(SC_LOGIN, SCLoginMsg.class);
        put(CS_SINGLE_CHAT, CSSingleChatMsg.class);
        put(SC_SINGLE_CHAT, SCSingleChatMsg.class);
        put(CS_CREATE_GROUP, CSCreateGroupMsg.class);
        put(SC_CREATE_GROUP, SCCreateGroupMsg.class);
        put(CS_JOIN_GROUP, CSJoinGroupMsg.class);
        put(SC_JOIN_GROUP, SCJoinGroupMsg.class);
        put(CS_QUIT_GROUP, CSQuitGroupMsg.class);
        put(SC_QUIT_GROUP, SCQuitGroupMsg.class);
        put(CS_GROUP_CHAT, CSGroupChatMsg.class);
        put(SC_GROUP_CHAT, SCGroupChatMsg.class);
    }};

    static Class<? extends Msg> fetchMsgClass(byte msgType) {
        return msgTypeMap.get(msgType);
    }
}
