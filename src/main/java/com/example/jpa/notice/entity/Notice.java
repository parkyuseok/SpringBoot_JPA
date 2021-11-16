package com.example.jpa.notice.entity;

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
 * @packageName : com.example.jpa.notice.entity
 * @fileName    : Notice.java
 * @author      : 박유석
 * @date        : 2021. 11. 2
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.02     박유석               최초 생성
 * </pre>
 */

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// 글을 작성할 때 사용자 한명만 작성함.
	// 그러나 사용자는 여러개의 글을 작성할 수 있다.
	@ManyToOne
	@JoinColumn
	private User user;
	
	@Column
	private String title;
	
	@Column
	private String contents;
	
	@Column
	private LocalDateTime regDate;
	
	@Column
	private LocalDateTime updateDate;
	
	@Column
	private int hits; //조회수
	
	@Column
	private int likes; //좋아요수
	
	@Column
	private boolean deleted;
	
	@Column
	private LocalDateTime deletedDate;
}
