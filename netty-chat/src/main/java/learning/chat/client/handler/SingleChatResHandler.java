package learning.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import learning.chat.protocol.s2c.SCSingleChatMsg;

/**
 * Author: linjx
 * Date: 2019/3/12
 */
public class SingleChatResHandler extends SimpleChannelInboundHandler<SCSingleChatMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SCSingleChatMsg scSingleChatMsg) throws Exception {
        String fromUserId = scSingleChatMsg.getFromUserId();
        String fromUserName = scSingleChatMsg.getFromUserName();
        System.out.println(fromUserName + "(" + fromUserId + ")" + ":" + scSingleChatMsg.getMessage());
    }
}
