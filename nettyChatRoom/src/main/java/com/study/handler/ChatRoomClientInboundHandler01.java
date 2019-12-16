package com.study.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @Author WangRui
 * @2019/12/13 18:52
 */
public class ChatRoomClientInboundHandler01 extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);


    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("失去连接。。。");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("成功建立连接。。。");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        SendMessageHandler sendMessageHandler = new SendMessageHandler(ctx, bufferedReader);
        new Thread(sendMessageHandler).start();


    }

}
