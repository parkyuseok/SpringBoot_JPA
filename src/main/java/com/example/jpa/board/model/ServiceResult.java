package com.example.jpa.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.board.model
 * @fileName    : ServiceResult.java
 * @author      : 박유석
 * @date        : 2021. 12. 22
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.12.22     박유석               최초 생성
 * </pre>
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ServiceResult {
	
	private boolean result;
	private String message;

	public static ServiceResult fail(String message) {
		return ServiceResult.builder()
				.result(false)
				.message(message)
				.build();
	}

	public static ServiceResult success() {
		return ServiceResult.builder()
				.result(true)
				.build();
	}
	
}
