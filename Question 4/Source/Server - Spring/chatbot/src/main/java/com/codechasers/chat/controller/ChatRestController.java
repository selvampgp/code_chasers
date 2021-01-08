package com.codechasers.chat.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codechasers.chat.resource.Message;
import com.codechasers.chat.resource.StaticValue;
import com.codechasers.chat.service.ChatService;

/**The communication b/w client and server will go through WebSockets and the specific action for the user’s intent, 
 * based on the response from the API
 * 
 */
@CrossOrigin
@RestController
public class ChatRestController implements StaticValue {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatRestController.class);
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	Map<String, Object> sessionStorage=new HashMap<>();

	
	/**Method used to get sessionID
	 * @param httpSession returns the current session
	 * @return returns a string containing the unique identifier value
	 */
	@GetMapping("/user/session")
	public String getSessionId(HttpSession httpSession){
		ChatService.sessionStorage.remove(httpSession.getId());
		ChatService.sessionStorageId.remove(httpSession.getId());
		return httpSession.getId(); 
	}
	
	
	/**Method used to sending messages
	 * @param message - Bot send message
	 * @param id - user Id
	 * @throws InterruptedException
	 */
	@MessageMapping("/{id}")
	public ResponseEntity<String> sendMessageToBot(Message message,@DestinationVariable String id) throws InterruptedException{
		LinkedList<String> messages=chatService.sendMessage(message, id);
		if(!messages.isEmpty())
		{
			messages.forEach(msg -> {
				simpMessagingTemplate.convertAndSend("/user/message/"+id, msg);
				try {
					Thread.sleep(3);
				} catch (Exception e) {
					logger.error("EXCEPTION THROWN : " + e.getMessage());
				}
			});
			return ResponseEntity.status(HttpStatus.OK).body("Success"); 	
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Message is Empty"); 
		
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String chat() {
		logger.info("CHATBOT INITIALIZED !!");
		return "CHATBOT INITIALIZED!!";
	}
	
}
