package com.study.handler;

import io.netty.channel.ChannelHandlerContext;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author WangRui
 * @date 2019/12/16 9:32
 */

/**
 * 发送消息处理类
 */
public class SendMessageHandler implements Runnable{

    //netty的ChannelHandlerContext对象
    private ChannelHandlerContext ctx=null;

    //输入流对象
    private BufferedReader bufferedReader=null;

    public SendMessageHandler(ChannelHandlerContext ctx, BufferedReader bufferedReader) {
        this.ctx = ctx;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        try {
            while (true){
                try {
                    System.out.print("请输入消息：");
                    String msg = bufferedReader.readLine();
                    ctx.channel().writeAndFlush(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
