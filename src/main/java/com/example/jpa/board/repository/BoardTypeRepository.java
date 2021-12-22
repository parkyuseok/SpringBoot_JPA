/**
 * 
 */
package com.example.jpa.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.board.entity.BoardType;

/**
 * @packageName : com.example.jpa.board.repository
 * @fileName    : BoardTypeRepository.java
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

@Repository
public interface BoardTypeRepository extends JpaRepository<BoardType, Long> {

	BoardType findByBoardName(String name);

}
