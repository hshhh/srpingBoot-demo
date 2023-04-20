package com.example.demowebsocket.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.example.demowebsocket.common.WebSocketConsts;
import com.example.demowebsocket.model.Server;
import com.example.demowebsocket.payload.ServerVO;
import com.example.demowebsocket.util.ServerUtil;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/server")
public class ServerController {
    private SimpMessagingTemplate wsTemplate;

    public ServerController(SimpMessagingTemplate wsTemplate) {
        this.wsTemplate = wsTemplate;
    }

    @GetMapping("/sendMessage/{message}")
    public void sendMessageToAll() {
        Dict dict = Dict.create().set("message","here");
        wsTemplate.convertAndSend(WebSocketConsts.PUSH_SERVER2, JSONUtil.toJsonStr(dict));
    }

    @GetMapping
    public Dict serverInfo() throws Exception{
        Server server = new Server();
        server.copyTo();
        ServerVO sv = ServerUtil.wrapServerVO(server);
        return ServerUtil.wrapServerDict(sv);
    }


}
