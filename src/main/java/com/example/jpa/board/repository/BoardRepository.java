/**
 * 
 */
package com.example.jpa.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.board.entity.Board;
import com.example.jpa.board.entity.BoardType;
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
public interface BoardRepository extends JpaRepository<Board, Long>{

	long countByBoardType(BoardType boardType);

	List<Board> findByUser(User user);

}
