package learning.chat.session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.internal.PlatformDependent;

import java.util.Map;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
public interface SessionManager {
    Map<String, Channel> userIdChannelMap = PlatformDependent.newConcurrentHashMap();
    Map<String, ChannelGroup> groupIdChannelGroupMap = PlatformDependent.newConcurrentHashMap();

    static void bind(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    static void unBind(Channel channel) {
        if (!hasLogin(channel)) {
            return;
        }
        Session session = getSession(channel);
        userIdChannelMap.remove(session.getUserId());
        channel.attr(Attributes.SESSION).set(null);
        System.out.println(session + " 退出登录!");
    }

    static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }

    static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }

    static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }
}
