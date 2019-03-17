package learning.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.c2s.CSLogoutMsg;
import learning.chat.protocol.s2c.SCLogoutMsg;
import learning.chat.session.SessionManager;

/**
 * Author: linjx
 * Date: 2019/3/12
 */
@ChannelHandler.Sharable
public class LogoutHandler extends SimpleChannelInboundHandler<CSLogoutMsg> {
    public static final LogoutHandler INSTANCE = new LogoutHandler();

    private LogoutHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CSLogoutMsg csLogoutMsg) throws Exception {
        SessionManager.unBind(channelHandlerContext.channel());
        SCLogoutMsg scLogoutMsg = new SCLogoutMsg();
        scLogoutMsg.setSuccess(true);
        channelHandlerContext.writeAndFlush(scLogoutMsg);
    }
}
