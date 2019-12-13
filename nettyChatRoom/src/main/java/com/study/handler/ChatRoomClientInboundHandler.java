package com.study.handler;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;

/**
 * @Author WangRui
 * @2019/12/13 15:19
 */
public class ChatRoomClientInboundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //super.channelActive(ctx);
        System.out.println("客户端已启动。。。");
        //Channel channel = ctx.channel();
        Scanner scanner=new Scanner(System.in);
       while (true){
           System.out.println("请输入数据：");
          if (scanner.hasNextLine()){
               ctx.writeAndFlush(scanner.nextLine());
           }
       }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        System.out.println("服务器返回消息。。。");
        System.out.println((msg.toString()));
    }
}
