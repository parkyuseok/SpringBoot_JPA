package com.example.jpa.notice.exception;

/**
 * @packageName : com.example.jpa.notice.exception
 * @fileName    : NoticeNotFoundException.java
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

public class NoticeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7436534257262811008L;

	public NoticeNotFoundException(String message) {
		super(message);
	}
}
