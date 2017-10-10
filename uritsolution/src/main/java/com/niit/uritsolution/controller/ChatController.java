package com.niit.uritsolution.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.uritsolution.model.Message;
import com.niit.uritsolution.model.OutMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutMessage sendMessage(Message message){
		
		System.out.println("Message recieved: "+message.getMessage());
		return new  OutMessage(message, new Date());
	}
}
