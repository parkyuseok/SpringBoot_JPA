package com.example.jpa.board.service;

import java.util.List;

import com.example.jpa.board.entity.BoardType;
import com.example.jpa.board.model.BoardTypeInput;
import com.example.jpa.board.model.BoardTypeUsing;
import com.example.jpa.board.model.ServiceResult;

/**
 * @packageName : com.example.jpa.board.service
 * @fileName    : BoardService.java
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
public interface BoardService {

	ServiceResult addBoard(BoardTypeInput boardTypeInput);

	ServiceResult updateBoard(Long id, BoardTypeInput boardTypeInput);

	ServiceResult deleteBoard(Long id);

	List<BoardType> getAllBoardType();

	/** 
	 * 게시판 타입의 사용여부를 설정
	 */
	ServiceResult setBoardTypeUsing(Long id, BoardTypeUsing boardTypeUsing);
	
}
