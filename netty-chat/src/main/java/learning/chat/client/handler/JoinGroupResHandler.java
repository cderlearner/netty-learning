package learning.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.s2c.SCJoinGroupMsg;

public class JoinGroupResHandler extends SimpleChannelInboundHandler<SCJoinGroupMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SCJoinGroupMsg scJoinGroupMsg) {
        if (scJoinGroupMsg.isSuccess()) {
            System.out.println("加入群[" + scJoinGroupMsg.getGroupId() + "]成功!");
        } else {
            System.err.println("加入群[" + scJoinGroupMsg.getGroupId() + "]失败，原因为：" + scJoinGroupMsg.getReason());
        }
    }
}
