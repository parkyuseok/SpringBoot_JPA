package com.example.jpa.user.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.entity.NoticeLike;
import com.example.jpa.notice.model.NoticeResponse;
import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.notice.repository.NoticeLikeRepository;
import com.example.jpa.notice.repository.NoticeRepository;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.exception.ExistsEmailException;
import com.example.jpa.user.exception.PasswordNotMatchException;
import com.example.jpa.user.exception.UserNotFoundException;
import com.example.jpa.user.model.UserInput;
import com.example.jpa.user.model.UserInputFind;
import com.example.jpa.user.model.UserInputPassword;
import com.example.jpa.user.model.UserLogin;
import com.example.jpa.user.model.UserLoginToken;
import com.example.jpa.user.model.UserResponse;
import com.example.jpa.user.model.UserUpdate;
import com.example.jpa.user.repository.UserRepository;
import com.example.jpa.util.JWTUtils;
import com.example.jpa.util.PasswordUtils;

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
	private final NoticeLikeRepository noticeLikeRepository;
	
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
	
	/**
	 * 사용자 아이디(이메일)를 찾는 API를 작성해 보세요.
	 * 이름과 전화번호에 해당하는 이메일을 찾는다.
	 */
	@GetMapping("/api/user")
	public ResponseEntity<?> findUser(@RequestBody UserInputFind userInputFind) {
		User user = userRepository.findByUserNameAndPhone(userInputFind.getUserName(), userInputFind.getPhone())
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		UserResponse userResponse = UserResponse.of(user);
		
		return ResponseEntity.ok().body(userResponse);
	}
	
	/**
	 * 사용자 비밀번호 초기화 요청(아이디 입력 후 전화번호로 문자 전송받음)의 기능을 API를 작성해 보세요.
	 * [조건]
	 * 아이디에 대한 정보 조회후
	 * 비밀번호를 초기화한 이후에 이를 문자 전송 로직 호출
	 * 초기화 비밀번호는 문자열 10자로 설정함
	 */
	@GetMapping("/api/user/{id}/password/reset")
	public ResponseEntity<?> resetUserPassword(@PathVariable Long id) {
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		// 비밀번호 초기화 - UUID 사용하기
		String resetPassword = getResetPassword();
		String resetEncryptPassword = getEncryptPassword(resetPassword);
		user.setPassword(resetEncryptPassword);
		userRepository.save(user);
		
		// 문자메시지 전송 - 일반적으로 모듈을 호출하는 형태
		String message = String.format("[%s]님의 임시 비밀번호가 [%s]로 초기화 되었습니다."
				, user.getUserName()
				, resetPassword);
		sendSMS(message);
		
		return ResponseEntity.ok().build();
	}
	
	public String getResetPassword() {
		// UUID.randomUUID() : a352ae81-4a9e-4e01-badd-5c5a2f71bf5d(예시)
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
	}
	
	// 원래는 외부 API를 사용하지만 현재는 그렇게 안되있으므로 간단하게 출력만 하는 형식으로 함.
	void sendSMS(String message) {
		System.out.println("[문자메시지전송]");
		System.out.println(message);
	}
	
	/**
	 * 내가 좋아요한 공지사항을 보는 API를 작성해 보세요.
	 */
	@GetMapping("/api/user/{id}/notice/like")
	public List<NoticeLike> likeNotice(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		List<NoticeLike> noticeLikeList = noticeLikeRepository.findByUser(user);
		
		return noticeLikeList;
	}
	
	/**
	 * 사용자 이메일과 비밀번호를 통해서 JWT를 방행하는 API를 작성해 보세요.
	 * [조건]
	 * - JWT 토큰발행시 사용자 정보가 유효하지 않을 때 예외 발생
	 * - 사용자정보가 존재하지 않는 경우(UserNotFoundException)에 대해서 예외 발생
	 * - 비밀번호가 일치하지 않는 경우(PasswordNotMatchException)에 대해서 예외 발생
	 * [참고]
	 * 보통 REST API라고 하면 API SERVER를 말한다.
	 * API SERVER는 요청한 데이터에 대해서 요청한 데이터를 응답하는데
	 * 보안상의 문제로 그냥 응답할 수 없고 인증된 형태로 주어야하는데 
	 * 기존의 웹페이지 화면에서 처리할 때는 로그인 세션을 통해서 인증을 처리할 수 있는데
	 * 보통 API SERVER에서 호출하는 경우 단말이라던지 세션 정보가 없는 곳에서 호출하기 때문에 인증에 대한 부분이 필요하다.
	 * 이러한 문제에 대한 대응으로 나온 기술이 JWT(JSON Web Token)라는 기술이다.
	 * 여기서 Token이란 특정한 형태의 정보를 담은 패킷이라고 보면 된다.
	 * 여기서는 라이브러리를 통해서 Token을 발행한다.
	 */
/*	
	@PostMapping("/api/user/login")
	public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {
		
		List<ResponseError> responseErrorList = new ArrayList<>();
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(e -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		//1. 사용자 정보 존재하는지 확인.
		User user = userRepository.findByEmail(userLogin.getEmail())
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		//2. 비밀번호 일치하는지 확인.
		if (!PasswordUtils.equalPassword(userLogin.getPassword(), user.getPassword())) {
			throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
		}
		
		return ResponseEntity.ok().build();
	}
*/
	/**
	 * 사용자의 이메일과 비밀번호를 통해서 JWT를 발행하는 로직을 작성해 보세요.
	 * - JWT 토큰발행
	 * [참고]
	 * https://jwt.io 사이트에서 토큰을 넣어보면 정보가 나온다.
	 */
	@PostMapping("/api/user/login")
	public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {
		
		List<ResponseError> responseErrorList = new ArrayList<>();
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(e -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		// 1. 사용자 정보 존재하는지 확인.
		User user = userRepository.findByEmail(userLogin.getEmail())
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		// 2. 비밀번호 일치하는지 확인.
		if (!PasswordUtils.equalPassword(userLogin.getPassword(), user.getPassword())) {
			throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
		}
		
		// JWT 토큰 발행시 발행 유효기간을 1개월로 저장
		LocalDateTime expiredDateTime = LocalDateTime.now().plusMonths(1);
		Date expiredDate = java.sql.Timestamp.valueOf(expiredDateTime);
		
		// 3. 토큰발행시점
		String token = JWT.create()
				// JWT 토큰 발행시 발행 유효기간 지정
				.withExpiresAt(expiredDate)
				// 실질적인 key: value 저장
				.withClaim("user_id", user.getId())
				// subject는 보통 사용자 이름을 넣는다.
				.withSubject(user.getUserName())
				// 누가 이슈화 했나?
				.withIssuer(user.getEmail())
				// 위에 값들에 대해 sign 해주여야한다.
				.sign(Algorithm.HMAC512("fastcampus".getBytes()));
					
		// "key": "value" 형태로 return 하기 위해 model을 만들어서 리턴해준다.
		return ResponseEntity.ok().body(UserLoginToken.builder().token(token).build());
	}
	
	/**
	 * JWT 토큰 재발행(특정 정보 인증에 대한) 하는 API를 작성해 보세요.
	 * - 이미 발행된 JWT 토큰을 통해서 토큰을 재발행하는 로직을 구현하세요.
	 * - 정상적인 회원에 대해서 재발행 진행
	 * [참고]
	 * 넘어오는 정보(Token)가 header로 오니까 getMapping으로 해도 크게 문제는 없지만
	 * Patch가 의미상 적당하므로 @PatchMapping을 사용한다. + 주소를 변경하지 않아도 됨
	 */
	@PatchMapping("/api/user/login")
	public ResponseEntity<?> refreshToken(HttpServletRequest request) {
		//Token이 header로 오므로 HttpServletRequest 객체를 받아서 뽑아서 사용한다.
		String token = request.getHeader("JWT-TOKEN"); //"JWT-TOKEN" -> 이름은 정하기 나름...
		String email = "";
		
		try {
			email = JWT.require(Algorithm.HMAC512("fastcampus".getBytes()))
					.build()
					.verify(token)
					.getIssuer();
		} catch (SignatureVerificationException e) {
			throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
		} catch (Exception e) {
			throw new PasswordNotMatchException("토큰 발행에 실패하였습니다.");
		}
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		LocalDateTime expiredDateTime = LocalDateTime.now().plusMonths(1);
		Date expiredDate = java.sql.Timestamp.valueOf(expiredDateTime);
		
		String newToken = JWT.create()
				.withExpiresAt(expiredDate)
				.withClaim("user_id", user.getId())
				.withSubject(user.getUserName())
				.withIssuer(user.getEmail())
				.sign(Algorithm.HMAC512("fastcampus".getBytes()));
					
		return ResponseEntity.ok().body(UserLoginToken.builder().token(newToken).build());
	}
	
	/**
	 * JWT 토큰에 대한 삭제를 요청하는 API를 작성해 보세요.
	 * [참고]
	 * @RequestHeader로 받을 수 있다.
	 */
	@DeleteMapping("/api/user/login")
	public ResponseEntity<?> removeToken(@RequestHeader("JWT-TOKEN") String token) {
		String email = "";
		
		try {
			email = JWTUtils.getIssuer(token);
		} catch (SignatureVerificationException e) {
			return new ResponseEntity<>("토큰 정보가 정확하지 않습니다.", HttpStatus.BAD_REQUEST);
		}
		
		// 세션을 갖고있을 때 추가적인 작업들...
		// (세션, 쿠키) 삭제
		// 클라이언트 쿠키/로컬스토리지/세션스토리지 삭제
		// 블랙리스트 작성?
		
		return ResponseEntity.ok().build();
	}
}
