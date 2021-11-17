/**
 * 
 */
package com.example.jpa.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

import lombok.experimental.UtilityClass;

/**
 * @packageName : com.example.jpa.util
 * @fileName    : PasswordUtils.java
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

// 유틸리티 클래스 이므로 @UtilityClass 작성 
@UtilityClass
public class PasswordUtils {

	// 패스워드를 암호화해서 리턴하는 함수
	
	// 입력한 패스워드를 해시된 패스워드랑 비교하는 함수
	// [참고] 유틸리티 클래스는 기본적으로 static으로 만든다.
	public static boolean equalPassword(String password, String encryptedPassword) {
		// package org.springframework.security.crypto.bcrypt
		return BCrypt.checkpw(password, encryptedPassword);
	}
}
