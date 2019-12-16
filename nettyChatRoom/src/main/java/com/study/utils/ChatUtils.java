package com.study.utils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;

/**
 * @author WangRui
 * @date 2019/12/14 15:05
 */
public class ChatUtils {

    /**
     * 聊天说话功能
     * @param channelGroup 通道群组
     * @param ctx 通道容器
     * @param userMap 用户地址，用户名映射
     * @param msg 要发送的信息
     */
    public static void sayToOthers(ChannelGroup channelGroup, ChannelHandlerContext ctx, Map<Channel,String> userMap, String msg){
        Channel channel=ctx.channel();
        if (msg.startsWith("@")){
            String userName=msg.substring(1,msg.indexOf(":"));
            msg=userMap.get(ctx.channel())+"对你说："+msg.substring(msg.indexOf(":")+1);
            for (Channel ch:channelGroup
                 ) {
                if (userMap.get(ch).equals(userName)&&!channel.equals(ch)){
                    ch.writeAndFlush(msg);
                    break;
                }
            }
        }else{
            for (Channel ch:channelGroup
                    ) {
                if (!ch.equals(channel)){
                    String finalMsg=userMap.get(channel)+":"+msg;
                    ch.writeAndFlush(finalMsg);
                }
            }
        }


    }
}
