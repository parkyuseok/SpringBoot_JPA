package com.example.jpa.user.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.UserNoticeCount;
import com.example.jpa.user.model.UserStatus;
import com.example.jpa.user.model.UserSummary;
import com.example.jpa.user.repository.UserCustomRepository;
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
	private final UserCustomRepository userCustomRepository;
	
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

	@Override
	public List<User> getTodayUsers() {
		
		// 현재시간이 2021-12-21 오전 11:17 이면
		// 오늘 00:00:00 ~ < 23:59:59
		LocalDateTime t = LocalDateTime.now();
		LocalDateTime startDate = LocalDateTime.of(t.getYear(), t.getMonth(), t.getDayOfMonth(), 0, 0, 0);
		LocalDateTime endDate = startDate.plusDays(1).minusSeconds(1);
		
		return userRepository.findToday(startDate, endDate);
	}

	@Override
	public List<UserNoticeCount> getUserNoticeCount() {
		return userCustomRepository.findUserNoticeCount();
	}
	
}