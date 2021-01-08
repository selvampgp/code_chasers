package com.codechasers.chat.resource;

/**To fetch the pre-defined value
 * 
 *
 */
public interface StaticValue {
	
	String REGEX_ONLY_STRING="^[A-Za-z]+$";
	String REGEX_EMAIL_FORMAT="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
	String REGEX_ONLY_NUMBERS = "^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*$";	
	String REGEX_ONLY_DATE_FORMAT="^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$";
	
	String SLOT_VERIFY_CALLERID_EMAILID="Verify_CallerID_EmailId";
	String SLOT_VERIFY_DOB="Verify_DOB";
	String SLOT_VERIFY_FEEDBACK="Verify_Feedback";	
	String SLOT_VERIFY_OPTIONS="Options";	
	String SLOT_VERIFY_MESSAGE_OPTION="Message_Option";	
	
	String SLOT_VERIFY_OPTIONS_END="optionEnd";
	
	String PHONE_NO="Please provide your email Id (or) mobile number? (ex: 9789224394) to continue?";
	String VALID_DOB_MESSAGE="Great, please share your date of birth (yyyy-mm-dd)";
	String VALID_DOB = "Please enter your valid DOB (yyyy-mm-dd)";
	String VALID_PHONE_EMAIL_ID="Please enter your valid  email Id (or) phone number (ex: 9789224394)";
	
	String VALID_FEEDBACK="Yes";
	
	String FINAL_MESSAGE="Thank You! For any queries please contact <b>helpdesk@ibot.com / 0422-2674300</b>.";
	String QUERIES="Do you want any updates about upcoming interview dates <b>(Yes/No)</b>?";
	
	String OPTIONS="Options";
	String WELCOME_MESSAGE="Thank you, for choosing i-Bot. Please choose your options. ";
	String MESSAGE_OPTION="Please choose your options. ";
	String UPCOMING="Next scheduled interview date on 20 Feb, 2021";
}
