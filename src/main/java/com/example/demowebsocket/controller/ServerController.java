package com.example.demowebsocket.controller;

import cn.hutool.core.lang.Dict;
import com.example.demowebsocket.model.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping
    public Dict serverInfo() throws Exception{
        Server server = new Server();
        server.copyTo();

        return Dict.create();
    }
}
