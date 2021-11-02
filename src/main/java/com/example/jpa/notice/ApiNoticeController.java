package com.example.jpa.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/*
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
	*/
	
	// 빈배열 리턴하기: front에서 null을 리턴하게 되면 방어코드가 필요하므로 빈배열을 리턴한다.(협업의 문제)
	@GetMapping("/api/notice")
	public List<NoticeModel> notice() {
		List<NoticeModel> noticeList = new ArrayList<>();
		
		return noticeList;
	}
	
	// (참고)String "20"로 리턴하는 것과 정수형(int)으로 리턴하는 것 둘다 모두 문자열로 내려간다.
	@GetMapping("/api/notice/count")
	public int noticeCount() {
		
		return 20;
	}
	
	/** 아래와 같이 요청시 등록할 수 있다.
	 * POST http://localhost:8080/api/notice?title=제목&contents=내용
	 * Content-Type: application/x-www-form-urlencoded
	 */
	// @RequestMapping(value = "/api/notice", method = RequestMethod.POST)
	// 위와 동일하고 @GetMapping과 주소는 동일하지만 method가 구분되므로 에러가 나지 않는다.
	/*
	@PostMapping("/api/notice")
	public NoticeModel addNotice(@RequestParam String title, @RequestParam String contents) {@RequestParam String title, @RequestParam String contents) {
		
		NoticeModel notice = NoticeModel.builder()
				.id(1)
				.title(title)
				.contents(contents)
				.regDate(LocalDateTime.now())
				.build();
		
		return notice;
	}
	*/
	
	@PostMapping("/api/notice")
	public NoticeModel addNotice(NoticeModel noticeModel) {
		
		noticeModel.setId(2);
		noticeModel.setRegDate(LocalDateTime.now());
		
		return noticeModel;
	}
}
