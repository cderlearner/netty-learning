package learning.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.c2s.CSSingleChatMsg;
import learning.chat.protocol.s2c.SCSingleChatMsg;
import learning.chat.session.Session;
import learning.chat.session.SessionManager;

/**
 * Author: linjx
 * Date: 2019/3/12
 */
@ChannelHandler.Sharable
public class SingleChatHandler extends SimpleChannelInboundHandler<CSSingleChatMsg> {
    public static final SingleChatHandler INSTANCE = new SingleChatHandler();

    private SingleChatHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CSSingleChatMsg csSingleChatMsg) throws Exception {
        Session session = SessionManager.getSession(channelHandlerContext.channel());

        SCSingleChatMsg scSingleChatMsg = new SCSingleChatMsg();
        scSingleChatMsg.setFromUserId(session.getUserId());
        scSingleChatMsg.setFromUserName(session.getUserName());
        scSingleChatMsg.setMessage(csSingleChatMsg.getMessage());

        Channel toUserChannel = SessionManager.getChannel(csSingleChatMsg.getToUserId());

        if (toUserChannel != null && SessionManager.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(scSingleChatMsg);
        } else {
            System.err.println("[" + csSingleChatMsg.getToUserId() + "] 不在线，发送失败!");
        }
    }
}
