package com.example.jpa.common.exception;

/**
 * @packageName : com.example.jpa.common.exception
 * @fileName    : BizException.java
 * @author      : 박유석
 * @date        : 2022. 2. 11
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2022.02.11     박유석               최초 생성
 * </pre>
 */
public class BizException extends RuntimeException {

	public BizException(String message) {
		super(message);
	}
	
}
