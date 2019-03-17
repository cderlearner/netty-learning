package learning.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import learning.chat.protocol.c2s.CSJoinGroupMsg;
import learning.chat.protocol.s2c.SCJoinGroupMsg;
import learning.chat.session.SessionManager;

@ChannelHandler.Sharable
public class JoinGroupHandler extends SimpleChannelInboundHandler<CSJoinGroupMsg> {
    public static final JoinGroupHandler INSTANCE = new JoinGroupHandler();

    private JoinGroupHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CSJoinGroupMsg csJoinGroupMsg) {
        String groupId = csJoinGroupMsg.getGroupId();
        ChannelGroup channelGroup = SessionManager.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        SCJoinGroupMsg responsePacket = new SCJoinGroupMsg();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);

        ctx.writeAndFlush(responsePacket);
    }
}
