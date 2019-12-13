package com.study.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

/**
 * @Author WangRui
 * @2019/12/13 18:52
 */
public class ChatRoomClientInboundHandler01 extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //System.out.println("客户端收到信息："+s);
        System.out.println("客户端已收到信息");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        System.out.println(msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //super.channelInactive(ctx);
        System.out.println("失去连接！");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("请输入：");
            if (scanner.hasNextLine()){
                ctx.channel().writeAndFlush(scanner.nextLine());
            }
        }

    }

}
