package com.study.initializer;

import com.study.handler.ChatRoomClientInboundHandler;
import com.study.handler.ChatRoomClientInboundHandler01;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * @Author WangRui
 * @2019/12/13 15:15
 */
public class ChatRoomClientInitilizer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline()
        .addLast(new StringEncoder())
                .addLast(new ChatRoomClientInboundHandler01())
        .addLast(new StringDecoder());
    }
}
