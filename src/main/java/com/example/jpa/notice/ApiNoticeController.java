package com.example.jpa.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.notice.model.NoticeModel;

/**
 * @packageName : com.example.jpa.notice
 * @fileName    : ApiNoticeController.java
 * @author      : 박유석
 * @date        : 2021. 10. 14
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.10.14     박유석               최초 생성
 * </pre>
 */

@RestController
public class ApiNoticeController {

	/*
	@GetMapping("/api/notice")
	public String noticeString() {
		
		return "공지사항입니다.";
	}
	*/
	
	/*
	@GetMapping("/api/notice")
	public NoticeModel notice() {
		
		LocalDateTime regDate = LocalDateTime.of(2021, 2, 8, 0, 0);
		
		NoticeModel notice = new NoticeModel();
		notice.setId(1);
		notice.setTitle("공지사항입니다.");
		notice.setContents("공지사항 내용입니다.");
		notice.setRegDate(regDate);
		
		return notice;
	}
	*/
	
	@GetMapping("/api/notice")
	public List<NoticeModel> notice() {
		
		List<NoticeModel> noticeList = new ArrayList<>();
		
		NoticeModel notice1 = new NoticeModel();
		notice1.setId(1);
		notice1.setTitle("공지사항입니다.");
		notice1.setContents("공지사항 내용입니다.");
		notice1.setRegDate(LocalDateTime.of(2021, 2, 8, 0, 0));
		noticeList.add(notice1);
		
		// builder pattern 사용 - List형태의 데이터에 사용하면 편리하다.
		NoticeModel notice2 = NoticeModel.builder()
				.id(2)
				.title("두번째 공지사항입니다.")
				.contents("두번째 공지사항 내용입니다.")
				.regDate(LocalDateTime.of(2021, 2, 8, 0, 0))
				.build();
		noticeList.add(notice2);
		
		return noticeList;
	}
}
