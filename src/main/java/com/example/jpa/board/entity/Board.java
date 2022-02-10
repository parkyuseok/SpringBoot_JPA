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
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// [참고]게시글 1개당 유저 1명 따라서 ManyToOne 어노테이션을 작성해준다.(1대1)
	@ManyToOne
	@JoinColumn
	private User user;
	
	@ManyToOne
	@JoinColumn
	private BoardType boardType;
	
	@Column
	private String title;
	
	@Column
	private String contents;
	
	@Column
	private LocalDateTime regDate;
	
//	@Column
//	private LocalDateTime updateDate;
	
	@Column
	private boolean topYn;
}
