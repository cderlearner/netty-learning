package learning.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.s2c.SCQuitGroupMsg;

public class QuitGroupResHandler extends SimpleChannelInboundHandler<SCQuitGroupMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SCQuitGroupMsg scQuitGroupMsg) {
        if (scQuitGroupMsg.isSuccess()) {
            System.out.println("退出群聊 [" + scQuitGroupMsg.getGroupId() + "]成功！");
        } else {
            System.out.println("退出群聊 [" + scQuitGroupMsg.getGroupId() + "]失败！");
        }

    }
}
