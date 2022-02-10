package com.example.jpa.common.model;

import org.springframework.http.ResponseEntity;

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
		return ResponseEntity.badRequest().body(message);
	}

	public static ResponseEntity<?> success() {
		return ResponseEntity.ok().build();
	}

}
