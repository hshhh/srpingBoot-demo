package com.example.demowebsocket.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

//SessionSubscribeEvent
@Component
@Slf4j
public class SessionSubscribeListener implements ApplicationListener<SessionSubscribeEvent> {
    //可对SessionSubscribeEvent事件监听，需实现ApplicationListener<E extends ApplicationEvent>接口，
    // 并注入容器(使用注解@Component可注入)
    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        /** Published when a new STOMP SUBSCRIBE is received.
         * 当收到一个订阅请求时，触发此事件
         */
        log.info("【来自浏览器的STOMP订阅的命令】"+sha.getCommand());//SUBSCRIBE
        log.info("【来自浏览器的STOMP订阅的消息】"+sha.getMessage());//null
    }
}
