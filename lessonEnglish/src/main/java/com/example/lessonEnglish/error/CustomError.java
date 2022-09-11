package com.example.lessonEnglish.error;

import lombok.Data;

@Data
public class CustomError extends Exception{
	private Integer code;
	private Object data;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomError(String message) {
		super(new Exception(message));
		this.code = 500;
	}
	
	public CustomError(String message, Throwable cause) {
		super(message,cause);
	}
	
	public CustomError(Throwable cause) {
		super(cause);
	}
}
