/**
 * 
 */
package com.example.jpa.user.model;

/**
 * @packageName : com.example.jpa.user.model
 * @fileName    : UserPointType.java
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
public enum UserPointType {

	NONE(0),
	
	USER_REGISTER(100),
	
	ADD_POST(200),
	
	ADD_COMMENT(150),
	
	ADD_LIKE(50);
	
	int value;
	public int getValue() {
		return this.value;
	}
	
	private UserPointType(int value) {
		 this.value = value;
	}
}
