package learning.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import learning.chat.protocol.c2s.CSQuitGroupMsg;
import learning.chat.protocol.s2c.SCQuitGroupMsg;
import learning.chat.session.SessionManager;

@ChannelHandler.Sharable
public class QuitGroupHandler extends SimpleChannelInboundHandler<CSQuitGroupMsg> {
    public static final QuitGroupHandler INSTANCE = new QuitGroupHandler();

    private QuitGroupHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CSQuitGroupMsg requestPacket) {
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionManager.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        SCQuitGroupMsg scQuitGroupMsg = new SCQuitGroupMsg();
        scQuitGroupMsg.setGroupId(requestPacket.getGroupId());
        scQuitGroupMsg.setSuccess(true);
        ctx.writeAndFlush(scQuitGroupMsg);
    }
}
