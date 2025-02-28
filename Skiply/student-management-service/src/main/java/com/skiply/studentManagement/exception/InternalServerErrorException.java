package com.skiply.studentManagement.exception;

public class InternalServerErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public InternalServerErrorException(String message) {
		super();
		this.message = message;
		
	}
	
	public InternalServerErrorException() {}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	} 
	


}
