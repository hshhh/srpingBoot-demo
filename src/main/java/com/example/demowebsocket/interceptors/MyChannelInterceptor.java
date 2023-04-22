package com.example.demowebsocket.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

/**
 *
 * ChannelInterceptor 拦截器拦截ws协议的请求响应(send/subscribe),
 * 不拦截http协议的请求/响应
 *
 */
@Slf4j
public class MyChannelInterceptor implements ChannelInterceptor {

    /**
     * 消息发送到目标方法之前被调用
     * @param message
     * @param channel
     * @return
     */
    @Override
   public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("preSend");
        return ChannelInterceptor.super.preSend(message,channel);
    }

    /**
     * 消息正在发送过程中被调用，主要用这个方法就行
     * 实现用户上线下线通知功能
     * @param message
     * @param channel
     * @param sent
     */

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent){
        StompHeaderAccessor header = StompHeaderAccessor.wrap(message);
        log.info("postSend->"+header.getDestination());
        if(header.getCommand()==null) return;
        if(header.getCommand().equals(StompCommand.CONNECT)){
            log.info("用户上线了");
        }
        if(header.getCommand().equals(StompCommand.DISCONNECT)){
            log.info("用户下线了");
        }
    }

    /**
     * 消息发送完成后被调用，一般用来做资源释放等工作
     * @param message
     * @param channel
     * @param sent
     * @param ex
     */

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, @Nullable Exception ex) {
            log.info("afterSendCompletion->");
        ChannelInterceptor.super.afterSendCompletion(message, channel, sent, ex);

    }

}
