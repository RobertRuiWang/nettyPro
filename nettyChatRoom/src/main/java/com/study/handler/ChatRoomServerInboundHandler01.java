package com.study.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author WangRui
 * @2019/12/13 18:47
 */
public class ChatRoomServerInboundHandler01 extends SimpleChannelInboundHandler<String> {

    //channel列表，用于管理channel
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("服务端收到"+channelHandlerContext.channel().remoteAddress()+"的信息："+s);
        //channelHandlerContext.channel().writeAndFlush(s);
//        ByteBuf buf= Unpooled.buffer();
//        byte[] bytes=s.getBytes();
//        buf.writeBytes(bytes);
//        channelHandlerContext.channel().writeAndFlush(bytes);
        Channel channel = channelHandlerContext.channel();
        for (Channel ch:channelGroup
             ) {
            if (ch.equals(channel)){
                ch.writeAndFlush("我对自己说："+s);
            }else{
                ch.writeAndFlush(channel.remoteAddress()+"对你说："+s);
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //super.handlerAdded(ctx);
        System.out.println(ctx.channel().remoteAddress()+"加入群聊");
        channelGroup.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //super.handlerRemoved(ctx);
        System.out.println(ctx.channel().remoteAddress()+"离开群聊");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //super.channelActive(ctx);
        System.out.println(ctx.channel().remoteAddress()+"上线。。。");
        ctx.channel().writeAndFlush("hello");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
