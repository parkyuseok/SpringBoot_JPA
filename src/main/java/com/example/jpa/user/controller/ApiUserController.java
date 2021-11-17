package com.example.jpa.user.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
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
import com.example.jpa.notice.model.NoticeResponse;
import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.notice.repository.NoticeRepository;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.exception.ExistsEmailException;
import com.example.jpa.user.exception.PasswordNotMatchException;
import com.example.jpa.user.exception.UserNotFoundException;
import com.example.jpa.user.model.UserInput;
import com.example.jpa.user.model.UserInputPassword;
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
	
	@ExceptionHandler(value = { ExistsEmailException.class, PasswordNotMatchException.class })
	public ResponseEntity<?> ExistsEmailExceptionHandler(RuntimeException exception) {
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
		
		if(userRepository.countByEmail(userInput.getEmail()) > 0) {
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
*/
	
	/**
	 * 사용자 비밀번호를 수정하는 API를 작성해 보세요.
	 * [조건]
	 * 이전비밀번호와 일치하는 경우 수정
	 * 일치하지 않는 경우 PasswordNotMatchException 발생
	 * 발생메시지는 "비밀번호가 일치하지 않습니다."
	 */
	@PatchMapping("/api/user/{id}/password")
	public ResponseEntity<?> updateUserPassword(@PathVariable Long id, @RequestBody @Valid UserInputPassword userInputPassword, Errors errors) {
		List<ResponseError> responseErrorList = new ArrayList<>();
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(e -> {
				responseErrorList.add(ResponseError.of((FieldError) e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}

		User user = userRepository.findByIdAndPassword(id, userInputPassword.getPassword())
				.orElseThrow(() -> new PasswordNotMatchException("비밀번호가 일치하지 않습니다."));
		
		user.setPassword(userInputPassword.getNewPassword());
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 암호화 해주는 function
	 * @param password
	 * @return encryptPassword
	 */
	private String getEncryptPassword(String password) {
		// Spring에서 제공해주는 암호화 클래스
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(password);
	}
	
	/**
	 * 회원가입시 비밀번호를 암호화하여 저장하는 API를 작성해보세요.
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
		
		if(userRepository.countByEmail(userInput.getEmail()) > 0) {
			throw new ExistsEmailException("이미 존재하는 이메일이 존재합니다.");
		}
		
		String encryptPassword = getEncryptPassword(userInput.getPassword());
		
		User user = User.builder()
				.email(userInput.getEmail())
				.userName(userInput.getUserName())
				.password(encryptPassword)
				.phone(userInput.getPhone())
				.regDate(LocalDateTime.now())
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 사용자 회원 탈퇴 기능에 대한 API를 작성해보세요.
	 * [조건]
	 * 회원정보가 존재하지 않은 경우 예외처리
	 * 만약, 사용자가 등록한 공지사항이 있는 경우는 회원삭제가 되지 않음
	 */
	@DeleteMapping("/api/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		/** [참고]
		 *  회사 정책에 따라 처리하는 방법이 다르다.
		 *  1. 삭제 못해... 삭제하려면, 공지사항부터 삭제하고와..
		 *  2. 회원삭제전에 공지사항글을 다 삭제하는 경우..
		 *  3. 회원들의 아이디를 null로 바꾼 뒤 관계를 끊어서 게시글을 유지하고 회원만 탈퇴하게 하는 경우..
		 *  ...
		 */
		
		// 참조하는 키가 있는 경우 예외 처리하기(DataIntegrityViolationException)
		try {
			userRepository.delete(user);
		} catch (DataIntegrityViolationException e) {
			String message = "제약조건에 문제가 발생하였습니다.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} catch (Exception e) { // Exception으로만 처리하면 보안 점검에 걸린다...
			String message = "회원 탈퇴 중 문제가 발생하였습니다.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok().build();
	}
	
}
