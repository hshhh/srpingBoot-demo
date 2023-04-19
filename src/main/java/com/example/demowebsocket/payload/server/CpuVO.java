package com.example.demowebsocket.payload.server;
import com.example.demowebsocket.model.server.Cpu;
import com.google.common.collect.Lists;
import com.example.demowebsocket.payload.KV;
import lombok.Data;

import java.util.List;

@Data
public class CpuVO {
    List<KV> data = Lists.newArrayList();

    public void add(KV kv){
        data.add(kv);
    }

    public static CpuVO create(Cpu cpu){
        CpuVO vo = new CpuVO();
        vo.add(new KV("核心数",cpu.getCpuNum()));
        vo.add(new KV("CPU总的使用率",cpu.getTotal()));
        vo.add(new KV("CPU系统使用率",cpu.getSys()+"%"));
        vo.add(new KV("CPU用户使用率",cpu.getUsed()+"%"));
        vo.add(new KV("CPU当前等待率",cpu.getWait()+"%"));
        vo.add(new KV("CPU当前空闲率",cpu.getFree()+"%"));
        return vo;
    }
}
