package com.study.handler;

import com.study.utils.ChatUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author WangRui
 * @ 2019/12/13 18:47
 */
public class ChatRoomServerInboundHandler01 extends SimpleChannelInboundHandler<String> {

    //channel列表，用于管理channel
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //输入流
    private static BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

    //用户名表
    private static Map<Channel,String> userMap=new HashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("服务端收到"+userMap.get(channelHandlerContext.channel())+"的信息："+s);
        ChatUtils.sayToOthers(channelGroup,channelHandlerContext,userMap,s);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        String msg="加入群聊";
        channelGroup.add(ctx.channel());
        System.out.print("请为该用户分配一个用户名：");
        String userName = bufferedReader.readLine();
        userMap.put(ctx.channel(),userName);
        System.out.println("用户名分配成功。。。");
        ChatUtils.sayToOthers(channelGroup,ctx,userMap,msg);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String msg = userMap.get(ctx.channel()) + "离开群聊";
        ChatUtils.sayToOthers(channelGroup,ctx,userMap,msg);
        userMap.remove(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = userMap.get(ctx.channel()) + "上线。。。";
        System.out.println(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
