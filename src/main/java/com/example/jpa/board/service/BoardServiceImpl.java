package com.example.jpa.board.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.jpa.board.entity.BoardType;
import com.example.jpa.board.model.BoardTypeInput;
import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.board.repository.BoardTypeRepository;

import lombok.RequiredArgsConstructor;

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

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardTypeRepository boardTypeRepository;
	
	@Override
	public ServiceResult addBoard(BoardTypeInput boardTypeInput) {
		
		BoardType boardType = boardTypeRepository.findByBoardName(boardTypeInput.getName());
		
		if (boardType != null && boardTypeInput.getName().equals(boardType.getBoardName())) {
			// 여기로 들어오면 동일한 게시판제목이 존재하는 것!
			return ServiceResult.fail("이미 동일한 게시판이 존재합니다.");
		}
		
		BoardType addBoardType = BoardType.builder()
				.boardName(boardTypeInput.getName())
				.regDate(LocalDateTime.now())
				.build();
		boardTypeRepository.save(addBoardType);
		
		return ServiceResult.success();
	}

	@Override
	public ServiceResult updateBoard(Long id, BoardTypeInput boardTypeInput) {
		
		Optional<BoardType> optionalBoardType = boardTypeRepository.findById(id);
		
		if (!optionalBoardType.isPresent()) {
			return ServiceResult.fail("수정할 게시판타입이 없습니다.");
		}
		
		BoardType boardType = optionalBoardType.get();
		
		if (boardType.getBoardName().equals(boardTypeInput.getName())) {
			return ServiceResult.fail("수정할 이름이 동일한 게시판명 입니다.");
		}
		
		boardType.setBoardName(boardTypeInput.getName());
		boardType.setUpdateDate(LocalDateTime.now());
		boardTypeRepository.save(boardType);
		
		return ServiceResult.success();
	}

}
