package learning.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.s2c.SCLoginMsg;
import learning.chat.session.Session;
import learning.chat.session.SessionManager;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
public class LoginResHandler extends SimpleChannelInboundHandler<SCLoginMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SCLoginMsg scLoginMsg) {
        String userId = scLoginMsg.getUserId();
        String userName = scLoginMsg.getUserName();

        if (scLoginMsg.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + scLoginMsg.getUserId());
            SessionManager.bind(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + scLoginMsg.getResult());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}