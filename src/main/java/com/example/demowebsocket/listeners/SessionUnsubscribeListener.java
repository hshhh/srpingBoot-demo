package com.example.demowebsocket.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

/**
 * SessionUnsubscribeEvent
 */
@Component
@Slf4j
public class SessionUnsubscribeListener implements ApplicationListener<SessionUnsubscribeEvent> {
    /**
     * @param event the event to respond to
     *   Published when a new STOMP UNSUBSCRIBE is received.
     *    收到取消订阅时触发此事件
     */
    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        log.info("【来自浏览器的STOMP取消订阅的命令】"+sha.getCommand());//SUBSCRIBE
        log.info("【来自浏览器的STOMP取消订阅的消息】"+sha.getMessage());//null
    }

}
