/**
 * 
 */
package com.example.jpa.user.model;

/**
 * @packageName : com.example.jpa.user.model
 * @fileName    : UserStatus.java
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
public enum UserStatus {

	None, Using, Stop;
	
	int value;
	
	UserStatus() {
		
	}
	
	public int getValue() {
		return this.value;
	}
	
}
