package com.example.jpa.notice.model;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.notice.model
 * @fileName    : ResponseError.java
 * @author      : 박유석
 * @date        : 2021. 11. 5
 * @version     : 1.0 
 * <pre>
 * @description : 어떤 필드에 어떤 에러가 났는지...
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.05     박유석               최초 생성
 * </pre>
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResponseError {
	private String field;
	private String message;
	
	public static ResponseError of(FieldError e) {
		return ResponseError.builder()
				.field(e.getField())
				.message(e.getDefaultMessage())
				.build();
	}
}
