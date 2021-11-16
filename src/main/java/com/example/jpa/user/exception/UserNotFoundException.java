package com.example.jpa.user.exception;

/**
 * @packageName : com.example.jpa.user.exception
 * @fileName    : UserNotFoundException.java
 * @author      : 박유석
 * @date        : 2021. 11. 16
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.16     박유석               최초 생성
 * </pre>
 */

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3239815039428319819L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
