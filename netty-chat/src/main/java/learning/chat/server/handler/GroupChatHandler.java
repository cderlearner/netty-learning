package learning.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.c2s.CSGroupChatMsg;
import learning.chat.protocol.s2c.SCGroupChatMsg;
import learning.chat.session.SessionManager;

@ChannelHandler.Sharable
public class GroupChatHandler extends SimpleChannelInboundHandler<CSGroupChatMsg> {
    public static final GroupChatHandler INSTANCE = new GroupChatHandler();

    private GroupChatHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CSGroupChatMsg csGroupChatMsg) {
        String groupId = csGroupChatMsg.getToGroupId();

        SCGroupChatMsg scGroupChatMsg = new SCGroupChatMsg();
        scGroupChatMsg.setFromGroupId(groupId);
        scGroupChatMsg.setMessage(csGroupChatMsg.getMessage());
        scGroupChatMsg.setFromUser(SessionManager.getSession(ctx.channel()));

        SessionManager.getChannelGroup(groupId).writeAndFlush(scGroupChatMsg);
    }
}
