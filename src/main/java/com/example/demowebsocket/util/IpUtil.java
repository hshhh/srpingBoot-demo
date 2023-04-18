package com.example.demowebsocket.util;

import jakarta.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {
    public static String getIpAddr(HttpServletRequest request) {
        if(request==null){
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;

    }
    public static String getHostIp() {
        //本机ip地址
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {

        }
        return "127.0.0.1";
    }

    public static String getHostName(){
        try{
            //本机域名
            return InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException e){

        }
        return "未知";
    }

}
