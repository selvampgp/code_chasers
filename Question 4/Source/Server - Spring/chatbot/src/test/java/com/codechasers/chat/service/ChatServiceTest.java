package com.codechasers.chat.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.codechasers.chat.ChatApplicationTest;
import com.codechasers.chat.builder.ChatServiceBuilder;
import com.codechasers.chat.resource.InterviewedUser;
import com.codechasers.chat.resource.Message;
import com.codechasers.chat.service.ChatService;

/**
 * @author sivagami.rm
 *
 */
public class ChatServiceTest extends ChatApplicationTest{
	
	@InjectMocks
	ChatService chatService;
	
	@Mock
	MongoOperations mongoOperations;
	
	
	@Test
	public void getCandidateName() {
		
		InterviewedUser interviewedUser = new ChatServiceBuilder().withDefault().build();
		
		List<InterviewedUser> interviewedUserList = new ArrayList<>();
		interviewedUserList.add(interviewedUser);
		
		Query searchQuery = new Query(Criteria.where("userId").is(interviewedUser.getUserId()));
		
		when(mongoOperations.find(searchQuery, InterviewedUser.class)).thenReturn(interviewedUserList);
		
		InterviewedUser actual = chatService.getCandidateName(interviewedUser.getUserId());

		Assert.assertNotNull(actual);
	}

	
	
	@Test
	public void getInterviewedUserByKey() {
		
		InterviewedUser interviewedUser = new ChatServiceBuilder().withDefault().build();
		
		List<InterviewedUser> interviewedUserList = new ArrayList<>();
		
		interviewedUserList.add(interviewedUser);
		
		Query searchQuery = new Query(Criteria.where("email").is(interviewedUser.getEmail()));
		
		when(mongoOperations.find(searchQuery, InterviewedUser.class)).thenReturn(interviewedUserList);
		
		InterviewedUser actual = chatService.getInterviewedUserByKey("email",interviewedUser.getEmail());

		Assert.assertNotNull(actual);
	}
	
	@Test
	public void sendMessage()
	{
		
		LinkedList<String> returnMessages = new LinkedList<>();
		InterviewedUser interviewedUser = new ChatServiceBuilder().withDefault().build();
		
		List<InterviewedUser> interviewedUserList = new ArrayList<>();
		interviewedUserList.add(interviewedUser);
		
		
		Message message=new Message();
		
		/**OPTIONS 1*/
		message.setMessage("1");
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
		/**INVALID PHONE**/
		message.setMessage("9789334355");
		Query invalidPhone= new Query(Criteria.where("phoneNo").is(interviewedUser.getPhoneNo()));
		when(mongoOperations.find(invalidPhone, InterviewedUser.class)).thenReturn(interviewedUserList);
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
									/**VALID PHONE**/
		message.setMessage("9789224394");
		Query searchQueryPhone = new Query(Criteria.where("phoneNo").is(interviewedUser.getPhoneNo()));
		when(mongoOperations.find(searchQueryPhone, InterviewedUser.class)).thenReturn(interviewedUserList);
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
		/**VALID DOB**/
		message.setMessage("1994-11-06");
		Query searchQuery = new Query(Criteria.where("userId").is(interviewedUser.getUserId()));
		when(mongoOperations.find(searchQuery, InterviewedUser.class)).thenReturn(interviewedUserList);
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
		/**VALIDATE FEEDBACK**/
		message.setMessage("Y");
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
							/**INVALID EMAIL**/
		message.setMessage("test@gmail.com");
		Query invalidEmail= new Query(Criteria.where("phoneNo").is(interviewedUser.getPhoneNo()));
		when(mongoOperations.find(invalidEmail, InterviewedUser.class)).thenReturn(interviewedUserList);
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
								/**VALID EMAIL**/
		message.setMessage("siva@gmail.com");
		Query searchQueryEmail = new Query(Criteria.where("email").is(interviewedUser.getEmail()));
		when(mongoOperations.find(searchQueryEmail, InterviewedUser.class)).thenReturn(interviewedUserList);
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
		/**VALID DOB**/
		message.setMessage("1994-11-06");
		Query searchQueryEmailJob = new Query(Criteria.where("userId").is(interviewedUser.getUserId()));
		when(mongoOperations.find(searchQueryEmailJob, InterviewedUser.class)).thenReturn(interviewedUserList);
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
		/**VALIDATE FEEDBACK**/
		message.setMessage("N");
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
				/**VALID EMAIL|INVALID DOB**/
		message.setMessage("siva@gmail.com");
		Query validEmail = new Query(Criteria.where("email").is(interviewedUser.getEmail()));
		when(mongoOperations.find(validEmail, InterviewedUser.class)).thenReturn(interviewedUserList);
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
		/**INVALID DOB**/
		message.setMessage("1994-07-06");
		Query invalidDOB = new Query(Criteria.where("userId").is(interviewedUser.getUserId()));
		when(mongoOperations.find(invalidDOB, InterviewedUser.class)).thenReturn(interviewedUserList);
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
		
		/**OPTIONS 2*/
		message.setMessage("2");
		returnMessages=chatService.sendMessage(message, interviewedUser.getUserId());
		Assert.assertNotNull(returnMessages);
	}
}
