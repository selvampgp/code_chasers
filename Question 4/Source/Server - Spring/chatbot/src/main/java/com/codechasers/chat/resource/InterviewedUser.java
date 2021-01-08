package com.codechasers.chat.resource;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**The Class InterviewedUser maintains interviewed user details.
 * 
 *
 */
@Document(collection = "InterviewedUser")
public class InterviewedUser {

	@Id
	private String id;

	@Indexed
	private String userId;
	
	@Field
	private String name;

	@Field
	private String email;
	
	@Field
	private String dob;
	
	@Field
	private String appliedForPost;

	@Field
	private String jobName;
	
	@Field
	private Date appliedDate;
	
	@Field
	private String feedback;
	
	@Field
	private String interviewStatus;

    @CreatedDate
    private Date createdDate;
    
    @Field
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
