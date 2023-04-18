package com.example.demowebsocket.payload.server;

import com.example.demowebsocket.model.server.Mem;
import com.example.demowebsocket.payload.KV;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class MemVO {
    List<KV> data = Lists.newArrayList();

    public void add(KV kv){
        data.add(kv);
    }
    public static MemVO create(Mem mem){
        MemVO vo = new MemVO();
        vo.add(new KV("内存总量",mem.getTotal()+"G"));
        vo.add(new KV("用过内存",mem.getUsed()+"G"));
        vo.add(new KV("剩余内存",mem.getFree()+"G"));
        vo.add(new KV("内存使用率",mem.getUsage()+"%"));
        return vo;
    }

}
