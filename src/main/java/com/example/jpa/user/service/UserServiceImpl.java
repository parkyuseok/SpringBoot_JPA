package com.example.jpa.user.service;

import org.springframework.stereotype.Service;

import com.example.jpa.user.model.UserStatus;
import com.example.jpa.user.model.UserSummary;
import com.example.jpa.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.user.service
 * @fileName    : UserServiceImpl.java
 * @author      : 박유석
 * @date        : 2021. 12. 21
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.12.21     박유석               최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override
	public UserSummary getUserStatusCount() {
		
		long usingUserCount = userRepository.countByStatus(UserStatus.Using);
		long stopUserCount = userRepository.countByStatus(UserStatus.Stop);
		long totalUserCount = userRepository.count();
		
		return UserSummary.builder()
					.usingUserCount(usingUserCount)
					.stopUserCount(stopUserCount)
					.totalUserCount(totalUserCount)
					.build();
	}
	
}