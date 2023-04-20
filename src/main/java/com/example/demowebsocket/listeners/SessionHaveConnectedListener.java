package com.example.demowebsocket.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

//SessionConnectedEvent
@Component
@Slf4j
public class SessionHaveConnectedListener implements ApplicationListener<SessionConnectedEvent> {
    /**
     * STOMP会话完全建立，触发此事件，表示浏览器与服务器已建立STOMP连接
     *At this point, the STOMP session can be considered fully established.
     * */
    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        log.info("【STOMP会话完全建立发送的命令】"+sha.getCommand()); //null
        log.info("【STOMP会话完全建立发送的消息】"+sha.getMessage()); //null
    }
}
