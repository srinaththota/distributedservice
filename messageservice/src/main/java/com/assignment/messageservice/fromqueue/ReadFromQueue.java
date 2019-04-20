package com.assignment.messageservice.fromqueue;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.assignment.messageservice.model.Message;
import com.assignment.messageservice.repository.MessageRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ReadFromQueue {

	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@JmsListener(destination="Message.broadcast")
	public void consume(String message) throws JMSException{
		System.out.println("=============="+message);
		try {
			Message obj = mapper.readValue(message, Message.class);
			messageRepository.save(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
