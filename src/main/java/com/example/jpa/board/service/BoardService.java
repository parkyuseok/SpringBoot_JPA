package com.example.jpa.board.service;

import javax.validation.Valid;

import com.example.jpa.board.model.BoardTypeInput;
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

	ServiceResult addBoard(@Valid BoardTypeInput boardTypeInput);
	
}
