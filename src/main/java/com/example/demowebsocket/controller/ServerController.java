package com.example.demowebsocket.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.example.demowebsocket.common.WebSocketConsts;
import com.example.demowebsocket.model.Server;
import com.example.demowebsocket.payload.ServerVO;
import com.example.demowebsocket.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ThreadPoolExecutor;


import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
@RequestMapping("/server")
public class ServerController {
    private SimpMessagingTemplate wsTemplate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public ServerController(SimpMessagingTemplate wsTemplate) {
        this.wsTemplate = wsTemplate;
    }


    @RequestMapping(path="/greetings", method=POST)
    @ResponseBody
    public Dict greet(@RequestParam("greeting") String greeting) {
        log.info("greet is " +greeting);

        this.wsTemplate.convertAndSend(WebSocketConsts.PUSH_SERVER2, greeting);
        Dict res = Dict.create().set("send_status","success");
        return res;
    }


    @GetMapping
    @ResponseBody
    public Dict serverInfo() throws Exception{
        Server server = new Server();
        server.copyTo();
        ServerVO sv = ServerUtil.wrapServerVO(server);
        return ServerUtil.wrapServerDict(sv);
    }


}
