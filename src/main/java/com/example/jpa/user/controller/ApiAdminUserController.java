/**
 * 
 */
package com.example.jpa.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.notice.repository.NoticeRepository;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.ResponseMessage;
import com.example.jpa.user.model.UserSearch;
import com.example.jpa.user.model.UserStatusInput;
import com.example.jpa.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.user.controller
 * @fileName    : ApiAdminUserController.java
 * @author      : 박유석
 * @date        : 2021. 11. 17
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.17     박유석               최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@RestController
public class ApiAdminUserController {

	private final UserRepository userRepository;
	private final NoticeRepository noticeRepository;
	
	/**
	 * 사용자 목록과 사용자 수를 함께 내리는 REST API를 작성해 보세요.
	 * [조건]
	 * ResponseDate의 구조를 아래와 같은 형식으로 작성해서 결과 리턴
	 * {
	 *     "totalCount": N,
	 *     "data": user 목록 컬렉션
	 * }
	 */
/*	
	@GetMapping("/api/admin/user")
	public ResponseMessage userList() {
		
		List<User> userList = userRepository.findAll();
		long totalCount = userRepository.count();
		
		return ResponseMessage.builder()
				.data(userList)
				.totalCount(totalCount)
				.build();
	}
*/	
	/**
	 * 사용자 상세화면을 조회하는 REST API를 아래의 조건에 맞게 작성해 보세요.
	 * [조건]
	 * - ResponseMessage 클래스로 추상화해서 전송
	 * {
	 * 		"header": {
	 * 			result: true|false,
	 * 			resultCode: string,
	 * 			message: error message or alert message,
	 * 			status: http result code
	 * 		},
	 * 		"body": 내려야 할 데이터가 있는 경우 body를 통해서 전송
	 * }
	 * [참고]
	 * 여기서의 header는 HTTP에서 사용하는 메서드의 해더와 다르게 이 API에서 header 정보를 갖고있는 형태
	 * HTTP에서 사용하는 메서드의 해더에 관한 내용보다는
	 * 실질적으로 데이터가 내려오고 데이터 부분에서 그 데이터에 대한 의미있는 해더와 의미있는 바디를 의미한다.
	 */
	@GetMapping("/api/admin/user/{id}")
	public ResponseEntity<?> userDetail(@PathVariable Long id) {
		
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok().body(ResponseMessage.success(user));
	}
	
	/**
	 * 사용자 목록 조회에 대한 검색을 리턴하는 API를 작성해 보세요.
	 * - 이메일, 이름, 전화번호에 대한 검색결과를 리턴(각 항목은 or 조건)
	 */
	@GetMapping("/api/admin/user/search")
	public ResponseEntity<?> findUser(@RequestBody UserSearch userSearch) {
		
		List<User> userList = 
				userRepository.findByEmailContainsOrPhoneContainsOrUserNameContains(
						userSearch.getEmail(), 
						userSearch.getPhone(), 
						userSearch.getUserName());
		
		return ResponseEntity.ok().body(ResponseMessage.success(userList));
	}
	
	/**
	 * 사용자의 상태를 변경하는 API를 작성해 보세요.
	 * - 사용자의 상태 : (정상, 정지)
	 * - 이에 대한 플래그 값은 사용자의 상태(정상:Using, 정지:Stop)
	 */
	@PatchMapping("/api/admin/user/{id}/status")
	public ResponseEntity<?> userStatus(@PathVariable Long id, @RequestBody UserStatusInput userStatusInput) {
		
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
		}
		
		User user = optionalUser.get();
		
		user.setStatus(userStatusInput.getStatus());
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 사용자 정보를 삭제하는 API를 작성해 보세요.
	 * - 작성된 게시글이 있으면 예외 발생 처리
	 */
	@DeleteMapping("/api/admin/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
		}
		
		User user = optionalUser.get();
		
		if (noticeRepository.countByUser(user) > 0) {
			return new ResponseEntity<>(ResponseMessage.fail("사용자가 작성한 공지사항이 있습니다."), HttpStatus.BAD_REQUEST);
		}
		
		userRepository.delete(user);
		
		return ResponseEntity.ok().build();
	}
	
}
