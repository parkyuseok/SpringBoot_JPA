package com.example.jpa.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.experimental.UtilityClass;

/**
 * @packageName : com.example.jpa.util
 * @fileName    : JWTUtil.java
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

@UtilityClass
public class JWTUtils {

	private static final String KEY = "fastcampus";
	
	public static String getIssuer(String token) {
		String issuer = JWT.require(Algorithm.HMAC512(KEY.getBytes()))
				.build()
				.verify(token)
				.getIssuer();
		return issuer;
	}
	
}
