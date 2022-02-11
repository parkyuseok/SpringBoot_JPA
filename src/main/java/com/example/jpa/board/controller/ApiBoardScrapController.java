package com.example.jpa.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.jpa.board.service.BoardService;
import com.example.jpa.common.model.ResponseResult;
import com.example.jpa.util.JWTUtils;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.notice
 * @fileName    : ApiNoticeController.java
 * @author      : 박유석
 * @date        : 2022.02.10
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.10.14     박유석               최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@RestController
public class ApiBoardScrapController {
	
	private final BoardService boardService;
	
	/**
	 * 게시글의 스크랩을 추가하는 API를 기능해 보세요.
	 */
	@PutMapping("/api/board/{id}/scrap")
	public ResponseEntity<?> boardScrap(@PathVariable Long id,
			@RequestHeader("JWT-TOKEN") String token) {
		// 1. 토큰 검증
		String email = "";
		try {
			email = JWTUtils.getIssuer(token);
		} catch (JWTVerificationException e) {
			return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
		}
		
		return ResponseResult.result(boardService.scrap(id, email));
	}
	
	/**
	 * 게시글의 스크랩을 삭제하는 API를 기능해 보세요.
	 */
	@DeleteMapping("/api/scrap/{id}")
	public ResponseEntity<?> deleteBoardScrap(@PathVariable Long id,
			@RequestHeader("JWT-TOKEN") String token) {
		// 1. 토큰 검증
		String email = "";
		try {
			email = JWTUtils.getIssuer(token);
		} catch (JWTVerificationException e) {
			return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
		}
		
		return ResponseResult.result(boardService.removeScrap(id, email));
	}
}
