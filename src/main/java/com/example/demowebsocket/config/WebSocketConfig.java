package com.example.demowebsocket.config;


import com.example.demowebsocket.interceptors.MyChannelInterceptor;
import com.example.demowebsocket.interceptors.MyHandshakeInterceptor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Map;

/**
 * <p>
 * WebSocket配置
 * broker:代理
 * </p>
 *
 * @author huang
 * @date
 */
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册一个 /notification 端点，前端通过这个端点进行连接
        ///notification is the HTTP URL for the endpoint to which a WebSocket (or SockJS) client
        // needs to connect for the WebSocket handshake
        registry.addEndpoint("/notification")
                .addInterceptors(new MyHandshakeInterceptor()) //设置拦截器
                .withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //定义了一个客户端订阅地址的前缀信息，也就是客户端接收服务端发送消息的前缀信息
        registry.enableSimpleBroker("/topic");
        // 客户端向服务端发送消息需有/app 前缀
        registry.setApplicationDestinationPrefixes("/app");
        // 指定用户发送（一对一）的前缀 /user/
        registry.setUserDestinationPrefix("/user");
    }


    /**
     * 添加 实现ChannelInterceptor的拦截器
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
                    registration.interceptors(new MyChannelInterceptor());
    }
    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(new MyChannelInterceptor());
    }
}