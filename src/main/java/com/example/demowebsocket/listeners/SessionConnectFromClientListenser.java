package com.example.demowebsocket.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

//SessionConnectEvent
@Slf4j
@Component
public class SessionConnectFromClientListenser implements ApplicationListener<SessionConnectEvent> {
    /**
     *
     * SessionConnectEvent
     * Published when a new STOMP CONNECT is received to indicate the start of a new client session
     * 当收到来自浏览器的STOMP连接时触发此事件
     * @param event the event to respond to
     */
     @Override
    public void onApplicationEvent(SessionConnectEvent event) {
         StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
         // 判断客户端的行为 应为CONNECT
         log.info("【来自浏览器的STOMP连接的命令】"+sha.getCommand()); //CONNECT
         log.info("【来自浏览器的STOMP连接的消息】"+sha.getMessage()); //null

     }
}
