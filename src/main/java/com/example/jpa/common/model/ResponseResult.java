package com.example.jpa.common.model;

import org.springframework.http.ResponseEntity;

import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.user.model.ResponseMessage;

/**
 * @packageName : com.example.jpa.common.model
 * @fileName    : ResponseResult.java
 * @author      : 박유석
 * @date        : 2022. 2. 10
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2022.02.10     박유석               최초 생성
 * </pre>
 */
public class ResponseResult {

	public static ResponseEntity<?> fail(String message) {
		return ResponseEntity.badRequest().body(ResponseMessage.fail(message));
	}

	public static ResponseEntity<?> success() {
		return ResponseEntity.ok().body(ResponseMessage.success());
	}

	public static ResponseEntity<?> result(ServiceResult result) {
		if (result.isFail()) {
			return fail(result.getMessage());
		}
		
		return success();
	}

}
