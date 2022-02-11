package com.example.jpa.board.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.jpa.board.entity.Board;
import com.example.jpa.board.entity.BoardBadReport;
import com.example.jpa.board.entity.BoardBookmark;
import com.example.jpa.board.entity.BoardComment;
import com.example.jpa.board.entity.BoardHits;
import com.example.jpa.board.entity.BoardLike;
import com.example.jpa.board.entity.BoardScrap;
import com.example.jpa.board.entity.BoardType;
import com.example.jpa.board.model.BoardBadReportInput;
import com.example.jpa.board.model.BoardPeriod;
import com.example.jpa.board.model.BoardTypeCount;
import com.example.jpa.board.model.BoardTypeInput;
import com.example.jpa.board.model.BoardTypeUsing;
import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.board.repository.BoardBadReportRepository;
import com.example.jpa.board.repository.BoardBookmarkRepository;
import com.example.jpa.board.repository.BoardCommentRepository;
import com.example.jpa.board.repository.BoardHitsRepository;
import com.example.jpa.board.repository.BoardLikeRepository;
import com.example.jpa.board.repository.BoardRepository;
import com.example.jpa.board.repository.BoardScrapRepository;
import com.example.jpa.board.repository.BoardTypeCustomRepository;
import com.example.jpa.board.repository.BoardTypeRepository;
import com.example.jpa.common.exception.BizException;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.repository.UserRepository;

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
	private final BoardRepository boardRepository;
	private final BoardTypeCustomRepository boardTypeCustomRepository;
	private final BoardHitsRepository boardHitsRepository;
	private final BoardLikeRepository boardLikeRepository;
	private final BoardBadReportRepository boardBadReportRepository;
	private final BoardScrapRepository boardScrapRepository;
	private final BoardBookmarkRepository boardBookmarkRepository;
	private final BoardCommentRepository boardCommentRepository;
	
	private final UserRepository userRepository;
	
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

	@Override
	public ServiceResult deleteBoard(Long id) {
		
		Optional<BoardType> optionalBoardType = boardTypeRepository.findById(id);
		
		if (!optionalBoardType.isPresent()) {
			return ServiceResult.fail("삭제할 게시판타입이 없습니다.");
		}
		
		BoardType boardType = optionalBoardType.get();
		
		long count = boardRepository.countByBoardType(boardType);
		if (count > 0) {
			return ServiceResult.fail("삭제할 게시판타입의 게시글이 존재합니다.");
		}
		
		boardTypeRepository.delete(boardType);
		
		return ServiceResult.success();
	}

	@Override
	public List<BoardType> getAllBoardType() {
		return boardTypeRepository.findAll();
	}

	@Override
	public ServiceResult setBoardTypeUsing(Long id, BoardTypeUsing boardTypeUsing) {
		Optional<BoardType> optionalBoardType = boardTypeRepository.findById(id);
		if (!optionalBoardType.isPresent()) {
			return ServiceResult.fail("삭제할 게시판타입이 없습니다.");
		}
		
		BoardType boardType = optionalBoardType.get();
		
		boardType.setUsingYn(boardTypeUsing.isUsingYn());
		boardTypeRepository.save(boardType);
		
		return ServiceResult.success();
	}

	@Override
	public List<BoardTypeCount> getBoardTypeCount() {
		return boardTypeCustomRepository.getBoardTypeCount();
	}

	@Override
	public ServiceResult setBoardTop(Long id, boolean topYn) {
		
		// 1. 게시판에 게시글이 있어야됨
		Optional<Board> optionalBoard = boardRepository.findById(id);
		if (!optionalBoard.isPresent()) {
			return ServiceResult.fail("게시글이 존재하지 않습니다.");
		}
		
		Board board = optionalBoard.get();
		
		// 2. 넣으려는 값과 기존에 있는 값이 같을 때
		if (board.isTopYn() == topYn) {
			if (topYn) {
				return ServiceResult.fail("이미 게시글이 최상단에 배치되어 있습니다.");
			} else {
				return ServiceResult.fail("이미 게시글이 최상단 배치가 해제되어 있습니다.");
			}
		}
		
		board.setTopYn(topYn);
		boardRepository.save(board);
		
		return ServiceResult.success();
	}

	@Override
	public ServiceResult setBoardPeriod(Long id, BoardPeriod boardPeriod) {
		// 1. 게시판에 게시글이 있어야됨
		Optional<Board> optionalBoard = boardRepository.findById(id);
		if (!optionalBoard.isPresent()) {
			return ServiceResult.fail("게시글이 존재하지 않습니다.");
		}
		
		Board board = optionalBoard.get();
		
		board.setPublishStartDate(boardPeriod.getStartDate());
		board.setPublishEndDate(boardPeriod.getEndDate());
		boardRepository.save(board);
		
		return ServiceResult.success();
	}

	@Override
	public ServiceResult setBoardHits(Long id, String email) {
		// 1. 게시판에 게시글이 있어야됨
		Optional<Board> optionalBoard = boardRepository.findById(id);
		if (!optionalBoard.isPresent()) {
			return ServiceResult.fail("게시글이 존재하지 않습니다.");
		}
		Board board = optionalBoard.get();
		// 2. 회원정보 확인
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		// 3. 조회수 여부 확인
		if(boardHitsRepository.countByBoardAndUser(board, user) > 0) {
			return ServiceResult.fail("이미 조회수가 있습니다.");
		}
		
		boardHitsRepository.save(BoardHits.builder()
				.board(board)
				.user(user)
				.regDate(LocalDateTime.now())
				.build());
		
		return ServiceResult.success();
	}

	@Override
	public ServiceResult setBoardLike(Long id, String email) {
		// 1. 게시판에 게시글이 있어야됨
		Optional<Board> optionalBoard = boardRepository.findById(id);
		if (!optionalBoard.isPresent()) {
			return ServiceResult.fail("게시글이 존재하지 않습니다.");
		}
		Board board = optionalBoard.get();
		// 2. 회원정보 확인
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		// 3. 좋아요 여부 확인
		long boardLikeCount = boardLikeRepository.countByBoardAndUser(board, user);
		if (boardLikeCount > 0) {
			return ServiceResult.fail("이미 좋아요한 내용이 있습니다.");
		}
		
		boardLikeRepository.save(BoardLike.builder()
				.board(board)
				.user(user)
				.regDate(LocalDateTime.now())
				.build());
		
		return ServiceResult.success();
	}

	@Override
	public ServiceResult setBoardUnLike(Long id, String email) {
		// 1. 게시판에 게시글이 있어야됨
		Optional<Board> optionalBoard = boardRepository.findById(id);
		if (!optionalBoard.isPresent()) {
			return ServiceResult.fail("게시글이 존재하지 않습니다.");
		}
		Board board = optionalBoard.get();
		// 2. 회원정보 확인
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		// 3. 좋아요 여부 확인
		Optional<BoardLike> optionalBoardLike = boardLikeRepository.findByBoardAndUser(board, user);
		if (!optionalBoardLike.isPresent()) {
			return ServiceResult.fail("좋아요한 내용이 없습니다.");
		}
		BoardLike boardLike = optionalBoardLike.get();
		
		boardLikeRepository.delete(boardLike);
		
		return ServiceResult.success();
	}

	@Override
	public ServiceResult addBadReport(Long id, String email, BoardBadReportInput boardBadReportInput) {
		// 1. 게시판에 게시글이 있어야됨
		Optional<Board> optionalBoard = boardRepository.findById(id);
		if (!optionalBoard.isPresent()) {
			return ServiceResult.fail("게시글이 존재하지 않습니다.");
		}
		Board board = optionalBoard.get();
		// 2. 회원정보 확인
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		
		BoardBadReport boardBadReport = BoardBadReport.builder()
				.userId(user.getId())
				.userName(user.getUserName())
				.userEmail(user.getEmail())
				.boardId(board.getId())
				.boardUserId(board.getUser().getId())
				.boardTitle(board.getTitle())
				.boardContents(board.getContents())
				.boardRegDate(board.getRegDate())
				.comments(boardBadReportInput.getComments())
				.regDate(LocalDateTime.now())
				.build();
		boardBadReportRepository.save(boardBadReport);
		
		return ServiceResult.success();
	}

	@Override
	public List<BoardBadReport> badReportList() {
		return boardBadReportRepository.findAll();
	}

	@Override
	public ServiceResult scrap(Long id, String email) {
		// 1. 게시판에 게시글이 있어야됨
		Optional<Board> optionalBoard = boardRepository.findById(id);
		if (!optionalBoard.isPresent()) {
			return ServiceResult.fail("게시글이 존재하지 않습니다.");
		}
		Board board = optionalBoard.get();
		// 2. 회원정보 확인
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		
		BoardScrap boardScrap = BoardScrap.builder()
				.user(user)
				.boardId(board.getId())
				.boardTypeId(board.getBoardType().getId())
				.boardTitle(board.getTitle())
				.boardContents(board.getContents())
				.boardRegDate(board.getRegDate())
				.regDate(LocalDateTime.now())
				.build();
		boardScrapRepository.save(boardScrap);
		
		return ServiceResult.success();
	}

	@Override
	public ServiceResult removeScrap(Long id, String email) {
		// 1. 스크랩 존재 여부 확인
		Optional<BoardScrap> optionalBoardScrap = boardScrapRepository.findById(id);
		if (!optionalBoardScrap.isPresent()) {
			return ServiceResult.fail("삭제할 스크랩이 없습니다.");
		}
		BoardScrap boardScrap = optionalBoardScrap.get();
		
		// 2. 회원정보 확인 ( 다른 사람이 삭제하지 못하게 하기 위해 내 스크랩인지 확인이 필요함 )
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		
		if (user.getId() != boardScrap.getUser().getId()) {
			return ServiceResult.fail("본인의 스크랩만 삭제할 수 있습니다.");
		}
		
		boardScrapRepository.delete(boardScrap);
		return ServiceResult.success();
	}

	private String getBoardUrl(long boardId) {
		return String.format("/board/%d", boardId);
	}
	
	@Override
	public ServiceResult addBookmark(Long id, String email) {
		// 1. 게시판에 게시글이 있어야됨
		Optional<Board> optionalBoard = boardRepository.findById(id);
		if (!optionalBoard.isPresent()) {
			return ServiceResult.fail("게시글이 존재하지 않습니다.");
		}
		Board board = optionalBoard.get();
		// 2. 회원정보 확인
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		
		BoardBookmark boardBookmark = BoardBookmark.builder()
				.user(user)
				.boardId(board.getId())
				.boardTypeId(board.getBoardType().getId())
				.boardTitle(board.getTitle())
				.boardUrl(getBoardUrl(board.getId()))
				.regDate(LocalDateTime.now())
				.build();
		
		boardBookmarkRepository.save(boardBookmark);
		return ServiceResult.success();
	}

	@Override
	public ServiceResult removeBookmark(Long id, String email) {
		// 1. 북마크 존재 여부 확인
		Optional<BoardBookmark> optionalBoardBookmark = boardBookmarkRepository.findById(id);
		if (!optionalBoardBookmark.isPresent()) {
			return ServiceResult.fail("삭제할 북마크가 없습니다.");
		}
		BoardBookmark boardBookmark = optionalBoardBookmark.get();
		
		// 2. 회원정보 확인
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		
		// 3. 내 북마크인지 확인 필요
		if (user.getId() != boardBookmark.getUser().getId()) {
			return ServiceResult.fail("본인의 북마크만 삭제할 수 있습니다.");
		}
		
		boardBookmarkRepository.delete(boardBookmark);
		return ServiceResult.success();
	}

	@Override
	public List<Board> postList(String email) {
		// 1. 회원정보 확인
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			// 비즈니스 로직에서 발생하는 익셉션을 던진다.
			throw new BizException("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		
		List<Board> list = boardRepository.findByUser(user);
		return list;
	}

	@Override
	public List<BoardComment> commentList(String email) {
		// 1. 회원정보 확인
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			// 비즈니스 로직에서 발생하는 익셉션을 던진다.
			throw new BizException("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		
		List<BoardComment> list = boardCommentRepository.findByUser(user);
		return list;
	}

}
