package com.example.demowebsocket.task;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.example.demowebsocket.common.WebSocketConsts;
import com.example.demowebsocket.model.Server;
import com.example.demowebsocket.payload.ServerVO;
import com.example.demowebsocket.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class ServerTask {

    private SimpMessagingTemplate wsTemplate;

    @Scheduled(cron="0/2 * * * * ?")
    public void websocket() throws Exception{
        log.info("消息推送开始执行：{}", DateUtil.formatDateTime(new Date()));
        Server server = new Server();
        server.copyTo();
        ServerVO sv = ServerUtil.wrapServerVO(server);
        Dict dict = ServerUtil.wrapServerDict(sv);
        wsTemplate.convertAndSend(WebSocketConsts.PUSH_SERVER,JSONUtil.toJsonStr(dict));
        log.info("消息推送结束执行：{}", DateUtil.formatDateTime(new Date()));
    }


}
