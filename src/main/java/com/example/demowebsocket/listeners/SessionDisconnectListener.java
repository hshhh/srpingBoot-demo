package com.example.demowebsocket.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class SessionDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {


    /**
     * @param event the event to respond to
     *   Published when a STOMP session ends,The DISCONNECT may have
     *   been sent from the client or it may be automatically generated
     *   when the WebSocket session is closed
     *     STOMP会话结束时触发此事件
     */
    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        log.info("【STOMP会话结束】"+event.toString());
    }
}
