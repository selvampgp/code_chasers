package com.codechasers.chat.builder;

import java.util.Date;

import com.codechasers.chat.resource.InterviewedUser;

public class ChatServiceBuilder implements IBuilder<InterviewedUser> {

	private final InterviewedUser interviewedUser;

	 public ChatServiceBuilder() {
		 interviewedUser=new InterviewedUser();
	}
	
	private String id;
	private String userId;
	private String name;
	private String email;
	private String dob;
	private String appliedForPost;
	private String jobName;
	private Date appliedDate;
	private String feedback;
	private String interviewStatus;
	private Date createdDate;
	private String phoneNo;
	 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAppliedForPost() {
		return appliedForPost;
	}

	public void setAppliedForPost(String appliedForPost) {
		this.appliedForPost = appliedForPost;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public InterviewedUser getInterviewedUser() {
		return interviewedUser;
	}

	@Override
	public InterviewedUser build() {
		return interviewedUser;
	}

	@Override
	public String getResourcePath() {
		return null;
	}

	
	public ChatServiceBuilder withDefault()
	{
		interviewedUser.setUserId("U123");
		interviewedUser.setAppliedForPost("Java Developer");
		interviewedUser.setAppliedDate(new Date());
		interviewedUser.setInterviewStatus("Queue");
		interviewedUser.setFeedback("Good");
		interviewedUser.setCreatedDate(new Date());
		interviewedUser.setPhoneNo("9789224394");
		interviewedUser.setEmail("siva@gmail.com");
		interviewedUser.setDob("1994-11-06");
		return this;
	}

}
