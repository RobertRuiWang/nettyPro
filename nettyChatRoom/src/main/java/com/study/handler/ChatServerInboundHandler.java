package com.study.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @Author WangRui
 * @2019/12/13 14:41
 */
public class ChatServerInboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
                //ByteBuf buf= (ByteBuf) msg;
                System.out.println(msg);
                //ByteBuf
                ByteBuf buf=Unpooled.buffer();
                buf.writeBytes(new String("我已收到你的信息:").getBytes());
                ctx.channel().writeAndFlush(buf);

        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println(ctx.channel().remoteAddress()+"连接");
    }


}
