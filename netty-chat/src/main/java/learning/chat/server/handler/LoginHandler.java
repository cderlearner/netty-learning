package learning.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.c2s.CSLoginMsg;
import learning.chat.protocol.s2c.SCLoginMsg;
import learning.chat.session.Session;
import learning.chat.session.SessionManager;

import java.util.Date;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
@ChannelHandler.Sharable
public class LoginHandler extends SimpleChannelInboundHandler<CSLoginMsg> {
    public static final LoginHandler INSTANCE = new LoginHandler();

    private Predicate valid = (t) -> true;

    private LoginHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CSLoginMsg csLoginMsg) {
        SCLoginMsg scLoginMsg = new SCLoginMsg();
        scLoginMsg.setUserName(csLoginMsg.getUserName());

        if (!valid.test(csLoginMsg)) {
            scLoginMsg.setResult("密码校验失败");
            scLoginMsg.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        } else {
            scLoginMsg.setSuccess(true);
            String userId = UUID.randomUUID().toString().split("-")[0];
            scLoginMsg.setUserId(userId);

            System.out.println("[" + csLoginMsg.getUserName() + "] 登录成功");
            SessionManager.bind(new Session(userId, csLoginMsg.getUserName()), ctx.channel());
        }

        // 登录响应
        ctx.writeAndFlush(scLoginMsg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionManager.unBind(ctx.channel());
    }
}
