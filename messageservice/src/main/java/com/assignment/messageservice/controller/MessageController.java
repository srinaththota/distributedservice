package com.assignment.messageservice.controller;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.assignment.messageservice.model.Message;
import com.assignment.messageservice.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	Queue queue;


	@GetMapping("/getmessages")
	public List<Message> retrieveMessages(){
		return messageRepository.findAll();

	}

	@GetMapping("/getmessages/{key}")
	public Message retrieveSpecificMessage(@PathVariable int key){
		Optional<Message> message=messageRepository.findById(key);
		return message.orElse(new Message(key,"Does not exists please post"));

	}

	@PostMapping("/message")
	public Message createMessage(@RequestBody Message message){	
		Optional<Message> messageExisting=messageRepository.findById(message.getId());

		if(messageExisting.isPresent()){
			if(messageExisting.get().equals(message)){
				return new Message(message.getId(),"Already exists");
			}
		}


		Message messageToStore=this.messageRepository.save(message);
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr="";
		try { 
			  
			jsonStr = Obj.writeValueAsString(message); 
  
            System.out.println(jsonStr); 
        } 
  
        catch (IOException e) { 
            e.printStackTrace(); 
        } 
		if(!jsonStr.isEmpty()){
			System.out.println(messageToStore.toString());
			jmsTemplate.convertAndSend(queue, jsonStr);
		}
		
		return messageToStore;
	}
}

