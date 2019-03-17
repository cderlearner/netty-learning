package learning.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import learning.chat.protocol.c2s.CSCreateGroupMsg;
import learning.chat.protocol.s2c.SCCreateGroupMsg;
import learning.chat.session.SessionManager;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Author: linjx
 * Date: 2019/3/12
 */
@ChannelHandler.Sharable
public class CreateGroupHandler extends SimpleChannelInboundHandler<CSCreateGroupMsg> {
    public static final CreateGroupHandler INSTANCE = new CreateGroupHandler();

    private CreateGroupHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CSCreateGroupMsg csCreateGroupMsg) {
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        List<String> userNameList = csCreateGroupMsg.getUserIdList().stream().map(e -> {
            Channel channel = SessionManager.getChannel(e);
            channelGroup.add(channel);
            return SessionManager.getSession(channel).getUserName();
        }).collect(Collectors.toList());

        String groupId = UUID.randomUUID().toString().split("-")[0];
        SCCreateGroupMsg scCreateGroupMsg = new SCCreateGroupMsg();
        scCreateGroupMsg.setSuccess(true);
        scCreateGroupMsg.setGroupId(groupId);
        scCreateGroupMsg.setUserNameList(userNameList);
        // 群发
        channelGroup.writeAndFlush(scCreateGroupMsg);

        System.out.print("群创建成功，id：" + scCreateGroupMsg.getGroupId() + ", 里面有：" + scCreateGroupMsg.getUserNameList());
        SessionManager.bindChannelGroup(groupId, channelGroup);
    }
}
