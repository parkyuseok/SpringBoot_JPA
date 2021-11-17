package com.example.jpa.user.exception;

/**
 * @packageName : com.example.jpa.user.model
 * @fileName    : PasswordNotMatchException.java
 * @author      : 박유석
 * @date        : 2021. 11. 17
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.17     박유석               최초 생성
 * </pre>
 */

public class PasswordNotMatchException extends RuntimeException {

	private static final long serialVersionUID = -4750263417593779655L;

	public PasswordNotMatchException(String message) {
		super(message);
	}

}
