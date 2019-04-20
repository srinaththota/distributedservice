package com.assignment.messageservice.config;


import java.util.HashMap;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import com.assignment.messageservice.model.*;
@Configuration
@ComponentScan(basePackages={"com.assignment.messageservice"})
public class Config {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	@Bean
	public Queue queue(){
		return new ActiveMQQueue("Message.broadcast2");
	}

	@Bean
	public ActiveMQConnectionFactory activeMqConnectionFactory(){
		ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory();
		factory.setBrokerURL(brokerUrl);
		factory.setTrustAllPackages(true);
		return factory;
	}

	@Bean
	public JmsTemplate jmsTemplate(){

		return new JmsTemplate(activeMqConnectionFactory());
	}
	
	
}
