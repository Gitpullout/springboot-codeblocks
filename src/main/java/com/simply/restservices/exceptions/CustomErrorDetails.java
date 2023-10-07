package com.simply.restservices.exceptions;

import java.util.Date;

// Simple Custom error details bean 
public class CustomErrorDetails {

	private Date timestamp;
	private String message;
	private String errordetails;
	
	//Field Constructor
	public CustomErrorDetails(Date timestamp, String message, String errordetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errordetails = errordetails;
	}
	
	// Getters
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getErrordetails() {
		return errordetails;
	}
	
	
	
	
}
