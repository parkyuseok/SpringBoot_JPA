package com.example.jpa.notice.exception;

/**
 * @packageName : com.example.jpa.notice.exception
 * @fileName    : AlreadyDeletedException.java
 * @author      : 박유석
 * @date        : 2021. 11. 3
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.03     박유석               최초 생성
 * </pre>
 */

public class AlreadyDeletedException extends RuntimeException {
	private static final long serialVersionUID = 5611392822508761682L;

	public AlreadyDeletedException(String message) {
		super(message);
	}
}
