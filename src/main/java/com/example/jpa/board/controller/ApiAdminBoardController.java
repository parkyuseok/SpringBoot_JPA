package com.example.jpa.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.board.entity.BoardBadReport;
import com.example.jpa.board.service.BoardService;
import com.example.jpa.common.model.ResponseResult;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.notice
 * @fileName    : ApiNoticeController.java
 * @author      : 박유석
 * @date        : 2022. 02. 10
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
public class ApiAdminBoardController {
	
	private final BoardService boardService;
	
	/**
	 * 게시글의 신고하기 목록을 조회하는 API를 작성해 보세요.
	 */
	@GetMapping("/api/admin/board/badreport")
	public ResponseEntity<?> badReport() {
		List<BoardBadReport> list = boardService.badReportList();
		return ResponseResult.success(list);
	}
	
}
