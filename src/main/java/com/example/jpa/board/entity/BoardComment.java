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
 * @date        : 2022. 02. 11
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
public class BoardComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn
	private User user;
	
	@ManyToOne
	@JoinColumn
	private Board board;
	
	@Column
	private String comments;
	
	@Column
	private LocalDateTime regDate;
	
//	@Column
//	private LocalDateTime updateDate;
}
