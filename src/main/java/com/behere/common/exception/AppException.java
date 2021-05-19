package com.behere.common.exception;

/**
 * 应用程序异常
 * 
 * @author fengjun
 * @version 1.0
 */
public class AppException extends Exception {
	private static final long serialVersionUID = 1L;

	public AppException(String msg) {
		super(msg);
	}

	public AppException() {
		super();
	}

}
