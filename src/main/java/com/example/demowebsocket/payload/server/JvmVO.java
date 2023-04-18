package com.example.demowebsocket.payload.server;

import com.example.demowebsocket.model.server.Jvm;
import com.example.demowebsocket.payload.KV;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class JvmVO {

    List<KV> data = Lists.newArrayList();

    public void add(KV kv){
        data.add(kv);
    }

    public static JvmVO create(Jvm jvm){
        JvmVO vo = new JvmVO();
        vo.add(new KV("当前JVM占用的内存总数",jvm.getTotal() + "M"));
        vo.add(new KV("JVM最大可用内存总数(M)",jvm.getMax()+"M"));
        vo.add(new KV("JVM空闲内存(M)",jvm.getFree()+"M"));
        vo.add(new KV("JDK版本",jvm.getVersion()));
        vo.add(new KV("JDK路径",jvm.getHome()));
        vo.add(new KV("JDK启动时间",jvm.getStartTime()));
        vo.add(new KV("JDK运行时间",jvm.getRunTime()));
        return vo;
    }
}
