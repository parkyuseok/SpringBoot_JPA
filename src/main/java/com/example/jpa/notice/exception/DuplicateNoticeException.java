package com.example.jpa.notice.exception;

/**
 * @packageName : com.example.jpa.notice.exception
 * @fileName    : DuplicateNoticeException.java
 * @author      : 박유석
 * @date        : 2021. 11. 5
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.05     박유석               최초 생성
 * </pre>
 */
public class DuplicateNoticeException extends RuntimeException {

	private static final long serialVersionUID = 5469245678654339481L;

	public DuplicateNoticeException(String message) {
		super(message);
	}
}
