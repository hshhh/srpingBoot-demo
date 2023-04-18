package com.example.demowebsocket.payload.server;

import com.example.demowebsocket.model.server.Sys;
import com.example.demowebsocket.payload.KV;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class SysVO {
    List<KV> data = Lists.newArrayList();

    public void add(KV kv){
        data.add(kv);
    }

    public static SysVO create(Sys sys){
        SysVO vo = new SysVO();
        vo.add(new KV("服务器名称",sys.getComputerName()));
        vo.add(new KV("服务器ip",sys.getComputerIp()));
        vo.add(new KV("项目所在目录",sys.getUserDir()));
        vo.add(new KV("操作系统",sys.getOsName()));
        vo.add(new KV("系统架构",sys.getOsArch()));
        return vo;
    }

}
