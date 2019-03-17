package learning.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import learning.chat.codec.MsgCodecHandler;
import learning.chat.server.handler.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
public class NettyServer {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                                0, 4));
                        ch.pipeline().addLast(MsgCodecHandler.INSTANCE);
                        ch.pipeline().addLast(LoginHandler.INSTANCE);
                        ch.pipeline().addLast(SingleChatHandler.INSTANCE);
                        ch.pipeline().addLast(CreateGroupHandler.INSTANCE);
                        ch.pipeline().addLast(GroupChatHandler.INSTANCE);
                        ch.pipeline().addLast(JoinGroupHandler.INSTANCE);
                        ch.pipeline().addLast(QuitGroupHandler.INSTANCE);
                    }
                })
                .bind(PORT).addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                                + ": 端口 [" + PORT + "] 绑定成功");
                    } else {
                        System.err.println("端口 [" + PORT + "] 绑定失败");
                    }
                });
        System.out.println("hello world！！！");
    }

}
