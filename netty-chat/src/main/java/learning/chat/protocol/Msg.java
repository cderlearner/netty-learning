package learning.chat.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
@Data
public abstract class Msg {

    // 消息号
    @JSONField(serialize = false)
    public abstract Byte getMsgType();
}
