package com.example.jpa.user.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.user.model
 * @fileName    : ResponseMessave.java
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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseMessage {

	private ResponseMessageHeader header;
	private Object body;

	public static ResponseMessage fail(String message) {
		return ResponseMessage.builder()
				.header(ResponseMessageHeader.builder()
						.result(false)
						.resultCode("")
						.message(message)
						.status(HttpStatus.BAD_REQUEST.value())
						.build())
				.body(null)
				.build();
	}

	public static ResponseMessage success(Object data) {
		return ResponseMessage.builder()
				.header(ResponseMessageHeader.builder()
						.result(true)
						.resultCode("")
						.message("")
						.status(HttpStatus.OK.value())
						.build())
				.body(data)
				.build();
	}

	public static ResponseMessage success() {
		return ResponseMessage.builder()
				.header(ResponseMessageHeader.builder()
						.result(true)
						.resultCode("")
						.message("")
						.status(HttpStatus.OK.value())
						.build())
				.body(null)
				.build();
	}
	
}
