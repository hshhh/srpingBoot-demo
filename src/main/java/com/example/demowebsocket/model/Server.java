package com.example.demowebsocket.model;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demowebsocket.model.server.*;
import com.example.demowebsocket.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.hardware.CentralProcessor.TickType;
import oshi.util.StringUtil;
import oshi.util.Util;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class Server {
    private static final int OSHI_WAIT_SECOND = 1000;

    //cpu相关信息
    private Cpu cpu = new Cpu();

    //内存相关信息
    private Mem mem = new Mem();

    //JVM相关信息
    private Jvm jvm = new Jvm();

    //服务器相关信息
    private Sys sys = new Sys();

    //磁盘相关信息
    private List<Sysfile> sysFiles = new LinkedList<Sysfile>();



    public Sys getSys(){
        return this.sys;
    }
    public void  setSys(Sys sys){
        this.sys = sys;
    }

    public Cpu getCpu(){
        return cpu;
    }
    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Mem getMem(){
        return mem;
    }

    public void setMem(Mem mem){
        this.mem = mem;
    }

    public Jvm getJvm(){
        return jvm;
    }

    public void setJvm(Jvm jvm){
        this.jvm = jvm;
    }

    public List<Sysfile> getSysFiles(){
        return sysFiles;
    }

    public void setSysFiles(List<Sysfile> sysFiles){
        this.sysFiles = sysFiles;
    }

    public void copyTo() throws UnknownHostException {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        GlobalMemory memory=hal.getMemory();
        OperatingSystem os = systemInfo.getOperatingSystem();
        setCpuInfo(cpu);
        setJvmInfo();
        setSysFiles(os);
        setMemInfo(memory);
        setSysInfo();
    }

    private void setCpuInfo(CentralProcessor processor){
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        //log.info("preTicks is " + Convert.toStr(prevTicks));
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        cpu.setCpuNum(processor.getLogicalProcessorCount());
        cpu.setTotal(totalCpu);
        cpu.setSys(cSys);
        cpu.setUsed(user);
        cpu.setWait(iowait);
        cpu.setFree(idle);
    }

    //设置内存信息
    private void setMemInfo(GlobalMemory memory){
        mem.setTotal(memory.getTotal());
        mem.setUsed(memory.getTotal()- memory.getAvailable());
        mem.setFree(memory.getAvailable());
    }

    /**
     * 设置服务器信息
     */
    private void setSysInfo(){
        //https://blog.csdn.net/weixin_41926301/article/details/86711787
        Properties prop = System.getProperties();
        sys.setComputerName(IpUtil.getHostName());
        sys.setComputerIp(IpUtil.getHostIp());
        //操作系统的名称
        sys.setOsName(prop.getProperty("os.name"));
        //操作系统的架构
        sys.setOsArch(prop.getProperty("os.arch"));
        //用户的当前目录
        sys.setUserDir(prop.getProperty("user.dir"));
    }

    /**
     * 设置Java虚拟机
     */
    private void setJvmInfo() throws UnknownHostException {
        Properties prop = System.getProperties();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
        jvm.setVersion(prop.getProperty("java.version"));
        jvm.setHome(prop.getProperty("java.home"));
    }

    /**
     *
     * 设置磁盘信息
     *
     * **/
    private void setSysFiles(OperatingSystem os){
        FileSystem fileSystem = os.getFileSystem();
        OSFileStore[] fsArr =fileSystem.getFileStores();
        for(OSFileStore fs:fsArr){
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            Sysfile sy = new Sysfile();
            sy.setDirName(fs.getMount());
            sy.setSysTypeName(fs.getType());
            sy.setTypeName(fs.getName());
            sy.setTotal(convertFileSize(total));
            sy.setFree(convertFileSize(free));
            sy.setUsed(convertFileSize(used));
            sy.setUsage(NumberUtil.mul(NumberUtil.div(used,total+1,4),100));
            sysFiles.add(sy);
        }

    }



    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }
}
