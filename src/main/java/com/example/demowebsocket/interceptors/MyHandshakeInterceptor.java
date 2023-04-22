package com.example.demowebsocket.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * HandshakeInterceptor 拦截器拦截 http 协议的请求/响应   不拦截 ws 协议的请求/响应。
 */
@Slf4j
@Component
public class MyHandshakeInterceptor implements HandshakeInterceptor {
    /**
     *
     * 在获取请求或响应之前进行拦截，获取一些请求或响应的数据
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes    如果该方法通过，可以在监听器或controller层拿到这里设置的数据
     * @return   返回false则拦截 返回true则通过
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if(request instanceof HttpServletRequest){
            HttpServletRequest request1 = (HttpServletRequest) request;

          //  log.info("拦截请求");
        }
        if(response instanceof HttpServletResponse){
            HttpServletResponse response1 = (HttpServletResponse) response;
           // log.info("拦截响应");
        }
        System.out.println("不许通过");
        return true;
    }

    /**
     * @param request
     * @param response
     * @param wsHandler
     * @param exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
