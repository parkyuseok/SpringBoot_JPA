package com.example.jpa.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.board.model.BoardTypeInput;
import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.board.service.BoardService;
import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.user.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.notice
 * @fileName    : ApiNoticeController.java
 * @author      : 박유석
 * @date        : 2021. 10. 14
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
public class ApiBoardController {
	
	private final BoardService boardService;
	
	/**
	 * 게시판타입을 추가하는 API를 작성해 보세요.
	 * - 동일한 게시판 제목이 있는 경우 status:200, result:false, message에 이미 동일한 게시판이 존재한다는 메시지 리턴
	 * - 게시판 이름은 필수항목에 대한 부분 체크
	 */
	@PostMapping("/api/board/type")
	public ResponseEntity<?> addBoardType(@RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {
		
		if (errors.hasErrors()) {
			List<ResponseError> responseErrors = ResponseError.of(errors.getAllErrors());
			return new ResponseEntity<>(ResponseMessage.fail("입력값이 정확하지 않습니다.", responseErrors), HttpStatus.BAD_REQUEST);
		}
		
		ServiceResult result = boardService.addBoard(boardTypeInput);
		
		if (!result.isResult()) {
			return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
		}
		
		return ResponseEntity.ok().build();
	}
	
}
