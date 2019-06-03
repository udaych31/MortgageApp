package com.hcl.mortgage.app.dto;

import java.io.Serializable;

public class ApplicationResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer statusCode;
	private String message;
	
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ApplicationResponse [statusCode=" + statusCode + ", message=" + message + "]";
	}
	

}
