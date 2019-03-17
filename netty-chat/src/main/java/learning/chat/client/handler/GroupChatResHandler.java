package learning.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.s2c.SCGroupChatMsg;
import learning.chat.session.Session;

public class GroupChatResHandler extends SimpleChannelInboundHandler<SCGroupChatMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SCGroupChatMsg scGroupChatMsg) {
        String fromGroupId = scGroupChatMsg.getFromGroupId();
        Session fromUser = scGroupChatMsg.getFromUser();
        System.out.println("收到群 [" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + scGroupChatMsg.getMessage());
    }
}
