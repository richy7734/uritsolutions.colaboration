package com.niit.uritsolution.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.uritsolution.model.Message;
import com.niit.uritsolution.model.OutMessage;

@Controller
public class ChatController {

	private static final Logger log = LoggerFactory.getLogger(ChatController.class);
	
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutMessage sendMessage(Message message){
		log.debug("Calling the method sendMessage()");
		log.debug("Message id :" + message.getId());
		log.debug("Message    : " + message.getMessage());
		log.debug("Message user ID: " + message.getUserId());

		System.out.println("Message recieved: "+message.getMessage());
		return new  OutMessage(message, new Date());
	}
}
