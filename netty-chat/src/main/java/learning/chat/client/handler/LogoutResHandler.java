package learning.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.s2c.SCLogoutMsg;
import learning.chat.session.SessionManager;

public class LogoutResHandler extends SimpleChannelInboundHandler<SCLogoutMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SCLogoutMsg scLogoutMsg) {
        SessionManager.unBind(ctx.channel());
    }
}
