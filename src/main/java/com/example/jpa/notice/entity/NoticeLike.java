package com.example.jpa.notice.entity;

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
public class NoticeLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// 내가(User) 어떠한 공지사항(Notice)에 대해서 좋아요를 한다. -> (Notice, User) Column 필요함 
	// 하나의 공지사항에 대해서 좋아요는 1번만 가능(@ManyToOne)
	@JoinColumn
	@ManyToOne
	private Notice notice;
	
	@JoinColumn
	@ManyToOne
	private User user;

}
