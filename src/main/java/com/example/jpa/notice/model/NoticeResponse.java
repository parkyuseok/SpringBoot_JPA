package com.example.jpa.notice.model;

import java.time.LocalDateTime;

import com.example.jpa.notice.entity.Notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.notice.model
 * @fileName    : NoticeResponse.java
 * @author      : 박유석
 * @date        : 2021. 11. 16
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.16     박유석               최초 생성
 * </pre>
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NoticeResponse {
	private long id;
	
	private long regUserId;     //작성자 ID
	private String regUserName; //작성자 이름
	
	private String title;
	private String contents;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private int hits;
	private int likes;

	public static NoticeResponse of(Notice notice) {
		return NoticeResponse.builder()
				.id(notice.getId())
				.title(notice.getTitle())
				.contents(notice.getContents())
				.regDate(notice.getRegDate())
				.updateDate(notice.getUpdateDate())
				.hits(notice.getHits())
				.likes(notice.getLikes())
				.regUserId(notice.getUser().getId())
				.regUserName(notice.getUser().getUserName())
				.build();
	}
}
