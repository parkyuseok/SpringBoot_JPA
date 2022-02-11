package com.example.jpa.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.common.model.ResponseResult;
import com.example.jpa.user.service.UserService;
import com.example.jpa.util.JWTUtils;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.user.controller
 * @fileName    : ApiUserController.java
 * @author      : 박유석
 * @date        : 2022. 02. 11
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.05     박유석               최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@RestController
public class ApiUserInterestController {
	
	private final UserService userService;
	
	/**
	 * 관심사용자에 등록하는 API를 작성해 보세요.
	 * - 내가 관심있어하는 사용자 등록하기
	 */
	@PutMapping("/api/user/{id}/interest")
	public ResponseEntity<?> interestUser(@PathVariable Long id, 
			@RequestHeader("JWT-TOKEN") String token) {
		
		String email = "";
		try {
			email = JWTUtils.getIssuer(token);
		} catch (JWTVerificationException e) {
			return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
		}
		
		ServiceResult result = userService.addInterestUser(email, id);
		return ResponseResult.result(result);
	}
	
	/**
	 * 관심사용자에서 관심사용자를 삭제하는 API를 작성해 보세요.
	 */
	@DeleteMapping("/api/user/interest/{id}")
	public ResponseEntity<?> deleteInterestUser(@PathVariable Long id, //UserInterest에 있는 Id
			@RequestHeader("JWT-TOKEN") String token) {
		
		String email = "";
		try {
			email = JWTUtils.getIssuer(token);
		} catch (JWTVerificationException e) {
			return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
		}
		
		return ResponseResult.result(userService.removeInterestUser(email, id));
	}
	
}
