package com.study.initializer;

import com.study.handler.ChatRoomClientInboundHandler;
import com.study.handler.ChatRoomClientInboundHandler01;
import com.study.handler.ChatRoomServerInboundHandler01;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * @Author WangRui
 * @2019/12/13 15:15
 */

/**
 * 客户端通道初始化
 */
public class ChatRoomClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new ChatRoomClientInboundHandler01());
    }
}
