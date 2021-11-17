package com.example.jpa.user.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.exception.ExistsEmailException;
import com.example.jpa.notice.model.NoticeResponse;
import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.notice.repository.NoticeRepository;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.exception.UserNotFoundException;
import com.example.jpa.user.model.UserInput;
import com.example.jpa.user.model.UserResponse;
import com.example.jpa.user.model.UserUpdate;
import com.example.jpa.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.user.controller
 * @fileName    : ApiUserController.java
 * @author      : 박유석
 * @date        : 2021. 11. 5
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.05     박유석               최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@RestController
public class ApiUserController {
	
	private final UserRepository userRepository;
	private final NoticeRepository noticeRepository;
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> UserNotFoundExceptionHandler(UserNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExistsEmailException.class)
	public ResponseEntity<?> ExistsEmailExceptionHandler(ExistsEmailException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
/*	
	@PostMapping("/api/user")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
	
		List<ResponseError> responseErrorList = new ArrayList<>(); 
		
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(e -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		User user = User.builder()
				.email(userInput.getEmail())
				.userName(userInput.getUserName())
				.password(userInput.getPassword())
				.phone(userInput.getPhone())
				.regDate(LocalDateTime.now())
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
	}
*/	
	@PutMapping("/api/user/{id}")
	public ResponseEntity<?> updateUIser(@PathVariable Long id, @RequestBody @Valid UserUpdate userUpdate, Errors errors) {
		
		List<ResponseError> responseErrorList = new ArrayList<>(); 
		
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(e -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		user.setPhone(userUpdate.getPhone());
		user.setUpdateDate(LocalDateTime.now());
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 사용자 정보 조회(가입한 아이디에 대한)의 기능을 수행하는 API를 작성해 보세요.
	 * 다만, 보안상 비밀번호와 가입일, 회원정보 수정일은 내리지 않는다.
	 */
	@GetMapping("/api/user/{id}")
	public UserResponse getUser(@PathVariable Long id) {
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		// UserResponse userResponse = new UserResponse(user);
		UserResponse userResponse = UserResponse.of(user);
		
		return userResponse;
	}
	
	/**
	 * 내가 작성한 공지사항 목록에 대한 API를 작성해 보세요.
	 * 삭제일과 삭제자 아이디는 보안상 내리지 않음
	 * 작성자정보를 모두 내리지 않고, 작성자의 아이디와 이름만 내림.
	 */
	@GetMapping("/api/user/{id}/notice")
	public List<NoticeResponse> userNotice(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		List<Notice> noticeList = noticeRepository.findByUser(user);
		
		List<NoticeResponse> noticeResponseList = new ArrayList<>();
		
		noticeList.stream().forEach((e) -> {
			noticeResponseList.add(NoticeResponse.of(e));
		});
		
		return noticeResponseList;
	}
	
	/**
	 * 사용자 등록시 이미 존재하는 이메일(이메일은 유일)인 경우 예외를 발생시키는 API를 작성해보세요.
	 * 동일한 이메일에 가입된 회원정보가 존재하는 경우 ExistsEmailException 발생
	 */
	@PostMapping("/api/user")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
		List<ResponseError> responseErrorList = new ArrayList<>();
		
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(e -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.CountByEmail(userInput.getEmail()) > 0) {
			throw new ExistsEmailException("이미 존재하는 이메일이 존재합니다.");
		}
		
		User user = User.builder()
				.email(userInput.getEmail())
				.userName(userInput.getUserName())
				.password(userInput.getPassword())
				.phone(userInput.getPhone())
				.regDate(LocalDateTime.now())
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
	}
}
