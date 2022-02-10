package com.example.jpa.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.jpa.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.board.entity
 * @fileName    : BoardType.java
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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class BoardLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn
	private Board board; // 어떤 특정한 게시글에 대해서
	
	@ManyToOne
	@JoinColumn
	private User user;   // 특정한 사용자가 조회
	
	@Column
	private LocalDateTime regDate; // 누가 언제 조회했는지
	
}
