package com.example.jpa.util;

import java.time.LocalDateTime;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.UserLoginToken;

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
	private static final String CLAIM_USER_ID = "user_id";
	
	public static UserLoginToken createToken(User user) {
		
		if (user == null) {
			return null;
		}
		
		// JWT 토큰 발행시 발행 유효기간을 1개월로 저장
		LocalDateTime expiredDateTime = LocalDateTime.now().plusMonths(1);
		Date expiredDate = java.sql.Timestamp.valueOf(expiredDateTime);
		
		// 토큰발행시점
		String token = JWT.create()
				// JWT 토큰 발행시 발행 유효기간 지정
				.withExpiresAt(expiredDate)
				// 실질적인 key: value 저장
				.withClaim(CLAIM_USER_ID, user.getId())
				// subject는 보통 사용자 이름을 넣는다.
				.withSubject(user.getUserName())
				// 누가 이슈화 했나?
				.withIssuer(user.getEmail())
				// 위에 값들에 대해 sign 해주여야한다.
				.sign(Algorithm.HMAC512(KEY.getBytes()));
		
		return UserLoginToken.builder()
				.token(token)
				.build();
	}
	
	public static String getIssuer(String token) {
		String issuer = JWT.require(Algorithm.HMAC512(KEY.getBytes()))
				.build()
				.verify(token)
				.getIssuer();
		return issuer;
	}
	
}
