package com.example.jpa.notice.exception;

/**
 * @packageName : com.example.jpa.notice.exception
 * @fileName    : ExistsEmailException.java
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

public class ExistsEmailException extends RuntimeException {

	private static final long serialVersionUID = 6221859106530774443L;

	public ExistsEmailException(String message) {
		super(message);
	}

}
