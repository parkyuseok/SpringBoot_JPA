package com.example.jpa.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.board.entity.Board;
import com.example.jpa.board.entity.BoardHits;
import com.example.jpa.board.entity.BoardLike;
import com.example.jpa.user.entity.User;

/**
 * @packageName : com.example.jpa.board.repository
 * @fileName    : BoardRepository.java
 * @author      : 박유석
 * @date        : 2021. 12. 23
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.12.23     박유석               최초 생성
 * </pre>
 */

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long>{

	long countByBoardAndUser(Board board, User user);

	Optional<BoardLike> findByBoardAndUser(Board board, User user);

}
