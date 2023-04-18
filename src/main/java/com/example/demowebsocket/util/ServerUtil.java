package com.example.demowebsocket.util;

import cn.hutool.core.lang.Dict;
import com.example.demowebsocket.model.Server;
import com.example.demowebsocket.payload.ServerVO;

public class ServerUtil {

    /**
     * 包装成 ServerVO
     *
     * @param server server
     * @return ServerVO
     */
    public static ServerVO wrapServerVO(Server server){
        ServerVO svo = new ServerVO();
        svo.create(server);
        return svo;
    }

    public static Dict wrapServerDict(ServerVO sv){
        Dict dict = Dict.create()
                .set("cpu",sv.getCpu().get(0).getData())
                .set("mem",sv.getMem().get(0).getData())
                .set("sys",sv.getSys().get(0).getData())
                .set("jvm",sv.getJvm().get(0).getData())
                .set("sysFile",sv.getSysFile().get(0).getData())
                ;
        return dict;
    }

}
