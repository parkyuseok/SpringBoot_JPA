package com.example.jpa.notice.model;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @packageName : com.example.jpa.notice
 * @fileName    : NoticeModel.java
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

// 2. @Data을 이용해서 getter setter를 편하게 사용할 수 있다.
@Data
public class NoticeModel {

	// ID, 제목, 내용, 등록일(작성일)
	private int id;
	private String title;
	private String contents;
	private LocalDateTime regDate; // 요즘 여러가지 이유로 Date type은 사용하지 않는다.
	
	// 1. private이므로 외부에서 사용하기 위해서는 getter, setter가 필요하다.
}
