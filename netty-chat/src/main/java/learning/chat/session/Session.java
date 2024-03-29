package learning.chat.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
@Data
@NoArgsConstructor
public class Session {
    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}