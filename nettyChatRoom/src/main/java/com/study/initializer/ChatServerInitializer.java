package com.study.initializer;

import com.study.handler.ChatRoomServerInboundHandler01;
import com.study.handler.ChatServerInboundHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author WangRui
 * @2019/12/13 14:57
 */
public class ChatServerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                .addLast(new StringDecoder())
                .addLast(new ChatRoomServerInboundHandler01())
                .addLast(new StringEncoder());

    }
}
