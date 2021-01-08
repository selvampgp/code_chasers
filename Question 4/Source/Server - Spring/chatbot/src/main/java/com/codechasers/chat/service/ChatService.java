package com.codechasers.chat.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.codechasers.chat.resource.InterviewedUser;
import com.codechasers.chat.resource.Message;
import com.codechasers.chat.resource.StaticValue;

/**Chatbot application uses natural language processors and this class is used to send and receive the message from bot/user
 *
 */
@Service
public class ChatService implements StaticValue{
	
	private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	public static Map<String, Object> sessionStorage = new HashMap<>();
	
	public static Map<String, String> sessionStorageId = new HashMap<>();

	/**Method used to get candidate details by used Id
	 * @param userId
	 * @return user details
	 */
	public InterviewedUser getCandidateName(String userId) {
		Query searchQuery = new Query(Criteria.where("userId").is(userId));
		List<InterviewedUser> users = mongoOperations.find(searchQuery, InterviewedUser.class);
		if(!users.isEmpty()){
			return users.get(0);
		}
		return null;
	}
	


	/**Method used to get candidate details by key(phone/email)
	 * @param keys Objects need to be search
	 * @param value Objects need to be search
	 * @return user details
	 */
	public InterviewedUser getInterviewedUserByKey(String keys,String value) {
		Query searchQuery = new Query(Criteria.where(keys).is(value));
		List<InterviewedUser> users = mongoOperations.find(searchQuery, InterviewedUser.class);
		if(!users.isEmpty()){
			return users.get(0);
		}
		return null;
	}
	
	
	/**Method used to sending message
	 * @param send message to bot
	 * @param id user Id
	 * @return List of message
	 */
	public LinkedList<String> sendMessage(Message message, String id) {
		logger.info("ACCESS SEND MESSAGE : " + sessionStorage);
		message.setUserId(id);
		LinkedList<String> returnMessages = new LinkedList<>();
		if(!sessionStorage.containsKey(id)) {
			sessionStorage.put(id,  SLOT_VERIFY_OPTIONS);
		}
		String userReplyMsg = message.getMessage().toLowerCase().trim().replaceAll("(\\t|\\r?\\n)+", "");
		if (!sessionStorage.isEmpty() && sessionStorage.get(id).equals(SLOT_VERIFY_OPTIONS)) {
			returnMessages = checkDataAvailable(id, userReplyMsg, SLOT_VERIFY_OPTIONS);
		}
		else if (!sessionStorage.isEmpty() && sessionStorage.get(id).equals(SLOT_VERIFY_OPTIONS_END)) {
			returnMessages = checkDataAvailable(id, userReplyMsg, SLOT_VERIFY_OPTIONS_END);
		}
		else if (!sessionStorage.isEmpty() && sessionStorage.get(id).equals(SLOT_VERIFY_MESSAGE_OPTION)) {
			returnMessages = checkDataAvailable(id, userReplyMsg, SLOT_VERIFY_MESSAGE_OPTION);
		}
		else if (!sessionStorage.isEmpty() && sessionStorage.get(id).equals(SLOT_VERIFY_CALLERID_EMAILID)) {
			returnMessages = checkDataAvailable(id, userReplyMsg, SLOT_VERIFY_CALLERID_EMAILID);
		}else if (!sessionStorage.isEmpty() && sessionStorage.get(id).equals(SLOT_VERIFY_DOB)) {
			returnMessages = checkDataAvailable(id, userReplyMsg, SLOT_VERIFY_DOB);
		}
		else if (!sessionStorage.isEmpty() && sessionStorage.get(id).equals(SLOT_VERIFY_FEEDBACK)) {
			returnMessages = checkDataAvailable(id, userReplyMsg, SLOT_VERIFY_FEEDBACK);
		}
		logger.info("RETURN MESSAGE : " + returnMessages);
		return returnMessages;
	}
	
	/**Method used to check whether the user details are available 
	 * @param userId 
	 * @param message
	 * @param slot 
	 * @return List of message
	 */
	private LinkedList<String> checkDataAvailable(String sessionId, String message, String slot) {
		try {
			switch (slot) {
			case SLOT_VERIFY_OPTIONS:
				return askForOptions(sessionId, message);
			case SLOT_VERIFY_MESSAGE_OPTION:
				return askForOptions(sessionId, message);
			case SLOT_VERIFY_OPTIONS_END:
				return askForOptions(sessionId, message);
			case SLOT_VERIFY_CALLERID_EMAILID:
				return verifyCallerEmailId(sessionId, message);
			case SLOT_VERIFY_DOB:
				return askForDOB(sessionStorageId.get(sessionId), message,sessionId);
			case SLOT_VERIFY_FEEDBACK:
				return askForFeedBack(sessionId,message);	
			default:
				break;
			}
		} catch (Exception ex) {
		}
		return null;
	}
	
	/**Method used to validate feedback from user 
	 * @param userId
	 * @param message
	 * @param sessionId
	 * @return List of feedback message
	 */
	private LinkedList<String> askForFeedBack(String sessionId,String message) {
		String validFeedBack = message.toUpperCase();
		LinkedList<String> msgs = new LinkedList<>();
		if (validFeedBack.equals("Y") || validFeedBack.contains("YES")) {
			msgs.add(UPCOMING);
		}
		msgs.add(FINAL_MESSAGE);
		sessionStorage.put(sessionId, SLOT_VERIFY_OPTIONS_END);
		logger.info("sessionStorage "+ sessionStorage);

		return msgs;
	}



	/**Method used to validate dob from user 
	 * @param userId
	 * @param slotsNew
	 * @param sessionId
	 * @return List of valid/invalid - dob message
	 */
	private LinkedList<String> askForDOB(String userId,String slotsNew, String sessionId) {
		 LinkedList<String> msgs = new LinkedList<>();
		if (validateSlot(slotsNew, REGEX_ONLY_DATE_FORMAT)) {
			InterviewedUser interviewedUser=getCandidateName(userId);
			if(Objects.nonNull(interviewedUser) && !interviewedUser.getDob().equals("") && interviewedUser.getDob().equals(slotsNew))
			{
				String table=table(interviewedUser.getJobName(),interviewedUser.getAppliedDate(),interviewedUser.getInterviewStatus(),interviewedUser.getFeedback());
				String name="Hi " + interviewedUser.getName() + ", Here is your interview details -";
				msgs.add(name);
				msgs.add(table);
				msgs.add(QUERIES);
				sessionStorage.put(sessionId, SLOT_VERIFY_FEEDBACK);
				return msgs;
			}
		}
		
		msgs.add(VALID_DOB);
		return msgs;
	}
	
	/**Method used to validate the slots
	 * @param value 
	 * @param regex
	 * @return true or false
	 */
	private boolean validateSlot(String value, String regex) {
		return value == null ? false : (value).matches(regex);
	}

	/***Method used to validate phone number/ email Id 
	 * @param sessionId
	 * @param slotsNew
	 * @return  List of valid/invalid - phone/email message
	 */
	private LinkedList<String> verifyCallerEmailId(String sessionId, String slotsNew) {

		LinkedList<String> msgs = new LinkedList<>();

		if (validateSlot(slotsNew, REGEX_ONLY_NUMBERS)) {
			InterviewedUser interviewedUser = this.getInterviewedUserByKey("phoneNo", slotsNew);
			if (Objects.nonNull(interviewedUser) && !interviewedUser.getPhoneNo().equals("")
					&& interviewedUser.getPhoneNo().equals(slotsNew)) {
				sessionStorage.put(sessionId, SLOT_VERIFY_DOB);
				sessionStorageId.put(sessionId, interviewedUser.getUserId());
				msgs.add(VALID_DOB_MESSAGE);
				return msgs;
			}
		} else if (validateSlot(slotsNew, REGEX_EMAIL_FORMAT)) {
			InterviewedUser interviewedUser = this.getInterviewedUserByKey("email", slotsNew);
			if (Objects.nonNull(interviewedUser) && !interviewedUser.getEmail().equals("")
					&& interviewedUser.getEmail().equals(slotsNew)) {
				sessionStorage.put(sessionId, SLOT_VERIFY_DOB);
				sessionStorageId.put(sessionId, interviewedUser.getUserId());
				msgs.add(VALID_DOB_MESSAGE);
				return msgs;
			}
		}

		msgs.add(VALID_PHONE_EMAIL_ID);
		return msgs;
	}

	
	/**Method used to validate feedback from user 
	 * @param userId
	 * @param message
	 * @param sessionId
	 * @return List of feedback message
	 */
	private LinkedList<String>askForOptions(String sessionId,String message) {
		String validFeedBack = message.toUpperCase();
		LinkedList<String> msgs = new LinkedList<>();
		
		logger.info("askForOptions :  "+sessionStorage.get(sessionId));
		if(sessionStorage.get(sessionId).equals(SLOT_VERIFY_OPTIONS_END))
		{
			sessionStorage.put(sessionId, SLOT_VERIFY_OPTIONS);
			msgs.add(WELCOME_MESSAGE);
		}
		else if (validFeedBack.equals("1")|| validFeedBack.equals("ONE") || validFeedBack.contains("PREVIOUS")) {
			sessionStorage.put(sessionId, SLOT_VERIFY_CALLERID_EMAILID);
			msgs.add(PHONE_NO);
		}
		else if (validFeedBack.equals("2") || validFeedBack.equals("TWO") || validFeedBack.contains("UPCOMING")) {
			String tableCurrentOpening=tableCurrentOpening();
			msgs.add(tableCurrentOpening);
			msgs.add(FINAL_MESSAGE);
			sessionStorage.put(sessionId, SLOT_VERIFY_OPTIONS_END);
		}
		else
		{
			sessionStorage.put(sessionId, SLOT_VERIFY_MESSAGE_OPTION);
			msgs.add(MESSAGE_OPTION);
		}
		return msgs;
	}
	
	/** HTML TABLE - To 
	 * @return 
	 */
	private String tableCurrentOpening()
	{
		String tableCurrentOpening="<table border=2px; style='font-size:12px'>" + 
				"<tr>" + 
				"    <td>Business Analyst</td>" + 
				"    <td>5 to 12 Years</td>" + 
				"    <td>Malaysia</td>" + 
				"	<td>Posted 2 days ago</td>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td>Manual Testing</td>" + 
				"    <td>4 to 8 Years</td>" + 
				"    <td>Malaysia</td>" + 
				"	<td>Posted 2 days ago</td>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td>Backend Developer</td>" + 
				"    <td>2 to 4 Years, 4 to 6 Years</td>" + 
				"    <td>Mumbai</td>" + 
				"  </tr>"+
				"  </table>";
		
		return tableCurrentOpening;
	}
	
	/**HTML TABLE
	 * @param jobName
	 * @param appliedDate
	 * @param status
	 * @param feedback
	 * @return
	 */
	private String table(String jobName, Date appliedDate, String status, String feedback)
	{
		String table="<table border=2px; style='font-size:12px'>" + 
				"<tr>" + 
				"    <th>Role</th>" + 
				"    <th>Applied Date</th>" + 
				"    <th>Status</th>" + 
				"	<th>Feedback</th>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td>"+jobName+"</td>" + 
				"    <td>"+appliedDate+"</td>" + 
				"    <td>"+status+"</td>" + 
				"	<td>"+feedback+"</td>" + 
				"  </tr>" + 
				"  </table>";
		
		return table;
	}
}

