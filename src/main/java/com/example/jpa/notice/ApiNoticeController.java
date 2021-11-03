package com.example.jpa.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.exception.AlreadyDeletedException;
import com.example.jpa.notice.exception.NoticeNotFoundException;
import com.example.jpa.notice.model.NoticeDeleteInput;
import com.example.jpa.notice.model.NoticeInput;
import com.example.jpa.notice.model.NoticeModel;
import com.example.jpa.notice.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

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

@RequiredArgsConstructor
@RestController
public class ApiNoticeController {

	/** 
	 *  1. 기존 spring에서 @Autowired로 주입받았는데
	 *  @Autowired
	 *  NoticeRepository noticeRepository;
	 *  
	 *  2-1. 요즘에는 생성자에서 주입받는다.
	 *  NoticeRepository noticeRepository;
	 *  
	 *  2-2. 패턴이 바뀌어서 다음과 같이 생성자에서 주입받게 된다.
	 *  public ApiNoticeController(NoticeRepository noticeRepository) {
	 *  	this.noticeRepository = noticeRepository;
	 *  }
	 */
	// 3-1. private final RepositoryType RepositoryName;
	// 3-2. @RequiredArgsConstructor을 사용하면 자동으로 필요한 생성자를 만들어서 NoticeRepository에 주입하게된다.
	private final NoticeRepository noticeRepository;
	
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
	
	/*
	@PostMapping("/api/notice")
	public NoticeModel addNotice(NoticeModel noticeModel) {
		
		noticeModel.setId(2);
		noticeModel.setRegDate(LocalDateTime.now());
		
		return noticeModel;
	}
	*/
	
	/** 아래와 같이 요청시 등록할 수 있다.
	 * POST http://localhost:8080/api/notice
	 * Content-Type: application/json
	 * data: {
	 * 	"title": "제목3",
	 * 	"contents": "내용3"
	 * }
	 */
	/*
	@PostMapping("/api/notice")
	public NoticeModel addNotice(@RequestBody NoticeModel noticeModel) { //json 형태로 받기 때문에 @RequestBody를 명시해주어야 spring에서 mapping을 할 수 있다.
		
		noticeModel.setId(3);
		noticeModel.setRegDate(LocalDateTime.now());
		
		return noticeModel;
	}
	*/
	
	/*
	@PostMapping("/api/notice")
	public Notice addNotice(@RequestBody NoticeInput noticeInput) {
		
		Notice notice = Notice.builder()
				.title(noticeInput.getTitle())
				.contents(noticeInput.getContents())
				.regDate(LocalDateTime.now())
				.build();
		
		noticeRepository.save(notice);
		
		return notice;
	}
	*/
	
	@PostMapping("/api/notice")
	public Notice addNotice(@RequestBody NoticeInput noticeInput) {
		
		Notice notice = Notice.builder()
				.title(noticeInput.getTitle())
				.contents(noticeInput.getContents())
				.regDate(LocalDateTime.now())
				.hits(0)
				.likes(0)
				.build();
		
		Notice resultNotice = noticeRepository.save(notice);
		
		return resultNotice;
	}
	
	@GetMapping("/api/notice/{id}")
	public Notice notice(@PathVariable Long id) {
		
		// Optional로 리턴한다는 의미?
		// 넘겨진 Notice가 Null을 포함할 수 있다는 의미
		Optional<Notice> notice = noticeRepository.findById(id);
		if (notice.isPresent()) {
			return notice.get();
		}
		
		return null;
	}
	
//	@PutMapping("/api/notice/{id}")
//	public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {
//		
//		Optional<Notice> notice = noticeRepository.findById(id);
//		if (notice.isPresent()) {
//			notice.get().setTitle(noticeInput.getTitle());
//			notice.get().setContents(noticeInput.getContents());
//			notice.get().setUpdateDate(LocalDateTime.now());
//			noticeRepository.save(notice.get());
//		}
//		
//	}
	
	@ExceptionHandler(NoticeNotFoundException.class)
	public ResponseEntity<String> handlerNoticeNotFoundException(NoticeNotFoundException exception) {
		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/api/notice/{id}")
	public void modNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {
		
	/*
		Optional<Notice> notice = noticeRepository.findById(id);
		if (!notice.isPresent()) {
			//예외 발생
			throw new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다.");
		}
	*/
		Notice notice = noticeRepository.findById(id)
				.orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
		
	/*
		// 공지사항 글이 있을 때(정상적인 로직 수행)
		notice.get().setTitle(noticeInput.getTitle());
		notice.get().setContents(noticeInput.getContents());
		notice.get().setUpdateDate(LocalDateTime.now());
		noticeRepository.save(notice.get());
	*/
		notice.setTitle(noticeInput.getTitle());
		notice.setContents(noticeInput.getContents());
		notice.setUpdateDate(LocalDateTime.now());
		noticeRepository.save(notice);
	}
	
	@PatchMapping("/api/notice/{id}/hits")
	public void noticeHits(@PathVariable Long id) {
		
		Notice notice = noticeRepository.findById(id)
				.orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
		
		notice.setHits(notice.getHits() + 1);
		
		noticeRepository.save(notice);
	}
	
//	@DeleteMapping("/api/notice/{id}")
//	public void deleteNotice(@PathVariable Long id) {
//		
//		Notice notice = noticeRepository.findById(id)
//				.orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
//		
//		noticeRepository.delete(notice);
//	}
	
	@ExceptionHandler(AlreadyDeletedException.class)
	public ResponseEntity<String> handlerNoticeNotFoundException(AlreadyDeletedException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
	}
	
	@DeleteMapping("/api/notice/{id}")
	public void deleteNotice(@PathVariable Long id) {
		
		Notice notice = noticeRepository.findById(id)
				.orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
		
		if (notice.isDeleted()) {
			throw new AlreadyDeletedException("이미 삭제된 글입니다.");
		}
		
		notice.setDeleted(true);
		notice.setDeletedDate(LocalDateTime.now());
		
		noticeRepository.save(notice);
	}
	
	@DeleteMapping("/api/notice")
	public void deleteNoticeList(@RequestBody NoticeDeleteInput noticeDeleteInput) {
		
		List<Notice> noticeList = noticeRepository.findByIdIn(noticeDeleteInput.getIdList())
			.orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
		
		noticeList.stream().forEach(e -> {
			e.setDeleted(true);
			e.setDeletedDate(LocalDateTime.now());
		});
		
		noticeRepository.saveAll(noticeList);
	}
	
	@DeleteMapping("/api/notice/all")
	public void deleteAll() {
		 noticeRepository.deleteAll();
	}
	
}
