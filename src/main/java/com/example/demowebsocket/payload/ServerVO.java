package com.example.demowebsocket.payload;

import com.example.demowebsocket.model.Server;
import com.example.demowebsocket.payload.server.*;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class ServerVO {

    List<CpuVO> cpu = Lists.newArrayList();
    List<JvmVO> jvm = Lists.newArrayList();
    List<MemVO> mem = Lists.newArrayList();
    List<SysVO> sys = Lists.newArrayList();
    List<SysFileVO> sysFile = Lists.newArrayList();

    public ServerVO create(Server server){
        cpu.add(CpuVO.create(server.getCpu()));
        jvm.add(JvmVO.create(server.getJvm()));
        mem.add(MemVO.create(server.getMem()));
        sys.add(SysVO.create(server.getSys()));
        sysFile.add(SysFileVO.create(server.getSysFiles()));
        return null;
    }




}
