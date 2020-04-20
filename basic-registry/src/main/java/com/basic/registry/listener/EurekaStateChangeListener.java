package com.basic.registry.listener;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EurekaStateChangeListener {
	
	

	private static final Logger log = LoggerFactory.getLogger(EurekaStateChangeListener.class);
	
	/*
	 * @Autowired private JavaMailSender mailSender;
	 */
	
	@EventListener
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        log.info("服务:{}宕机",eurekaInstanceCanceledEvent.getAppName());
        //sendSimpleMail("875079028@qq.com",eurekaInstanceCanceledEvent.getAppName().toLowerCase()+"服务宕机 "+new Date());
    }
 
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
    	log.info("服务:{}上线",event.getInstanceInfo().getAppName());
    }
 
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        event.getAppName();
        event.getServerId();
    }
 
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
 
    }
 
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
       
    }
    
    
	/*
	 * public void sendSimpleMail(String to,String content) { SimpleMailMessage
	 * message = new SimpleMailMessage(); message.setFrom("875079028@qq.com");
	 * message.setTo(to); message.setSubject("大禹智慧水务提醒您"); message.setText(content);
	 * try { mailSender.send(message); log.info("简单邮件发送成功！"); } catch (Exception e)
	 * { log.error("发送简单邮件时发生异常！"+e); }
	 * 
	 * }
	 */

}
