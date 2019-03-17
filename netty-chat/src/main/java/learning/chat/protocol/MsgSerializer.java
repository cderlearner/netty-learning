package learning.chat.protocol;

import com.alibaba.fastjson.JSON;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
public interface MsgSerializer {

    MsgSerializer DEFAULT = new JSONSerializer();

    byte[] toBytes(Object object);

    <T> T fromBytes(Class<T> clazz, byte[] bytes);





    class JSONSerializer implements MsgSerializer {

        @Override
        public byte[] toBytes(Object object) {
            return JSON.toJSONBytes(object);
        }

        @Override
        public <T> T fromBytes(Class<T> clazz, byte[] bytes) {
            return JSON.parseObject(bytes, clazz);
        }
    }

}
