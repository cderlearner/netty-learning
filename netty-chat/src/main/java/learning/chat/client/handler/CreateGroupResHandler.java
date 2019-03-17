package learning.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.s2c.SCCreateGroupMsg;

public class CreateGroupResHandler extends SimpleChannelInboundHandler<SCCreateGroupMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SCCreateGroupMsg msg) {
        System.out.print("群创建成功，id：[" + msg.getGroupId() + "], 里面有：" + msg.getUserNameList());
    }
}
