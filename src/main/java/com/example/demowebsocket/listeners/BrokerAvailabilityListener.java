package com.example.demowebsocket.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BrokerAvailabilityListener implements ApplicationListener<BrokerAvailabilityEvent> {
    /**
    *  BrokerAvailabilityEvent
    * Indicates when the broker becomes available or unavailable.
     * While the “simple” broker becomes available immediately on startup
     * and remains so while the application is running,
     * the STOMP “broker relay” can lose its connection to the full featured broker
     * (for example, if the broker is restarted).
     * The broker relay has reconnect logic
     * and re-establishes the “system” connection to the
     * broker when it comes back. As a result,
     * this event is published whenever the state changes
     * from connected to disconnected and vice-versa
    *
    * */
    //监听websocket代理是否可用，项目启动一般就是可用的，对于代理中介而言不一样
    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
       if(event.isBrokerAvailable()==true){
           log.info("the broker becomes available"+ "代理已变成可用的");
       }
       else{
           log.info("the broker becomes unavailable"+ "代理已经不可用了");
       }
    }
}
