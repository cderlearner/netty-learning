package learning.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import learning.chat.client.handler.*;
import learning.chat.codec.MsgDecoder;
import learning.chat.codec.MsgEncoder;

import java.util.Date;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
public class NettyClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                                0, 4));
                        ch.pipeline().addLast(new MsgDecoder());
                        ch.pipeline().addLast(new LoginResHandler());
                        ch.pipeline().addLast(new SingleChatResHandler());
                        ch.pipeline().addLast(new LogoutResHandler());
                        ch.pipeline().addLast(new CreateGroupResHandler());
                        ch.pipeline().addLast(new JoinGroupResHandler());
                        ch.pipeline().addLast(new GroupChatResHandler());
                        ch.pipeline().addLast(new QuitGroupResHandler());
                        ch.pipeline().addLast(new MsgEncoder());
                    }
                })
                .connect(HOST, PORT).addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println(new Date() + ": 连接成功，启动控制台线程…");
                        Channel channel = ((ChannelFuture) future).channel();
                        Console.startThread(channel);
                    } else {
                        System.err.println("连接失败");
                    }
                });
    }

}