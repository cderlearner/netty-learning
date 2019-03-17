package learning.chat.client;

import io.netty.channel.Channel;
import learning.chat.protocol.c2s.*;
import learning.chat.session.SessionManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
@FunctionalInterface
public interface Console {
    void exec(Scanner scanner, Channel channel);

    Console login = (s, c) -> {
        System.out.print("输入用户名登录: ");

        CSLoginMsg csLoginMsg = new CSLoginMsg();
        csLoginMsg.setUserName(s.nextLine());
        csLoginMsg.setPassword("pwd");
        c.writeAndFlush(csLoginMsg);

        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
    };

    Console toUser = (s, c) -> {
        System.out.print("【单聊】发送消息给xx用户: ");

        String toUserId = s.next();
        String message = s.next();
        c.writeAndFlush(new CSSingleChatMsg(toUserId, message));
    };

    Console logout = (s, c) -> c.writeAndFlush(new CSLogoutMsg());

    Console createGroup = (s, c) -> {
        System.out.print("【创建群聊】输入userId列表，英文逗号隔开：");
        String userIds = s.next();

        CSCreateGroupMsg csCreateGroupMsg = new CSCreateGroupMsg();
        csCreateGroupMsg.setUserIdList(Arrays.asList(userIds.split(",")));
        c.writeAndFlush(csCreateGroupMsg);
    };

    Console joinGroup = (s, c) -> {
        System.out.print("输入groupId加入群聊：");
        String groupId = s.next();

        CSJoinGroupMsg csJoinGroupMsg = new CSJoinGroupMsg();
        csJoinGroupMsg.setGroupId(groupId);
        c.writeAndFlush(csJoinGroupMsg);
    };

    Console quitGroup = (s, c) -> {
        System.out.print("输入groupId退出群聊：");
        String groupId = s.next();

        CSQuitGroupMsg csQuitGroupMsg = new CSQuitGroupMsg();
        csQuitGroupMsg.setGroupId(groupId);
        c.writeAndFlush(csQuitGroupMsg);
    };

    Console toGroup = (s ,c) -> {
        System.out.print("发送消息给xx群组：");
        String toGroupId = s.next();
        String message = s.next();

        c.writeAndFlush(new CSGroupChatMsg(toGroupId, message));
    };

    Map<String, Console> consoleCommandMap = new HashMap<String, Console>() {{
        put("toUser", toUser);
        put("logout", logout);
        put("createGroup", createGroup);
        put("joinGroup", joinGroup);
        put("quitGroup", quitGroup);
        put("toGroup", toGroup);
    }};

    static void startThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionManager.hasLogin(channel)) {
                    login.exec(scanner, channel);
                } else {
                    // 输入指令
                    String command = scanner.next();
                    if (!SessionManager.hasLogin(channel)) {
                        System.err.println("你不在登录状态！");
                        return;
                    }
                    Console console = consoleCommandMap.get(command);
                    if (console != null) {
                        console.exec(scanner, channel);
                    } else {
                        System.err.println("无法识别 [" + command + "] 指令，请重新输入!");
                    }
                }
            }
        }).start();
    }

}
