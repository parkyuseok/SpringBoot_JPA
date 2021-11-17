/**
 * 
 */
package com.example.jpa.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.ResponseMessage;
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
	
	/**
	 * 사용자 목록과 사용자 수를 함께 내리는 REST API를 작성해 보세요.
	 * [조건]
	 * ResponseDate의 구조를 아래와 같은 형식으로 작성해서 결과 리턴
	 * {
	 *     "totalCount": N,
	 *     "data": user 목록 컬렉션
	 * }
	 */
	@GetMapping("/api/admin/user")
	public ResponseMessage userList() {
		
		List<User> userList = userRepository.findAll();
		long totalCount = userRepository.count();
		
		return ResponseMessage.builder()
				.data(userList)
				.totalCount(totalCount)
				.build();
	}
}
