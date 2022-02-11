package com.example.jpa.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.entity.UserInterest;
import com.example.jpa.user.model.UserLogCount;
import com.example.jpa.user.model.UserNoticeCount;
import com.example.jpa.user.model.UserStatus;
import com.example.jpa.user.model.UserSummary;
import com.example.jpa.user.repository.UserCustomRepository;
import com.example.jpa.user.repository.UserInterestRepository;
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
	private final UserInterestRepository userInterestRepository;
	
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

	@Override
	public List<UserLogCount> getUserLogCount() {
		return userCustomRepository.findUserLogCount();
	}

	@Override
	public List<UserLogCount> getUserLikeBest() {
		return userCustomRepository.findUserLikeBest();
	}

	@Override
	public ServiceResult addInterestUser(String email, Long id) {
		// 1. 누가 등록하는데? -> 존재하는 사용자야?
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		
		// 2. 등록할 사람이 누군데?
		Optional<User> optionalInterestUser = userRepository.findById(id);
		if (!optionalInterestUser.isPresent()) {
			return ServiceResult.fail("관심사용자에 추가할 회원 정보가 존재하지 않습니다.");
		}
		User interestUser = optionalInterestUser.get();
		
		// 3. 이미 등록되어 있니?
		if(userInterestRepository.countByUserAndInterestUser(user, interestUser) > 0) {
			return ServiceResult.fail("이미 관심사용자 목록에 추가하였습니다.");
		}
		
		// 4. 내가 나를 추가해?
		if (user.getId() == interestUser.getId()) {
			return ServiceResult.fail("자기자신은 추가할 수 없습니다.");
		}
		
		UserInterest userInterest = UserInterest.builder()
				.user(user)
				.interestUser(interestUser)
				.regDate(LocalDateTime.now())
				.build();
		userInterestRepository.save(userInterest);
		
		return ServiceResult.success();
	}

	@Override
	public ServiceResult removeInterestUser(String email, Long id) {
		// 1. 누가 삭제하는데?
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		
		// 2. 삭제할 관심사용자가 누군데?
		Optional<UserInterest> optionalUserInterest = userInterestRepository.findById(id);
		if (!optionalUserInterest.isPresent()) {
			return ServiceResult.fail("관심사용자에 삭제할 회원 정보가 존재하지 않습니다.");
		}
		UserInterest userInterest = optionalUserInterest.get();
		
		// 3. 등록한 사람이랑 삭제할 사람이랑 같니?
		if (user.getId() != userInterest.getUser().getId()) {
			return ServiceResult.fail("본인의 관심자 정보만 삭제할 수 있습니다.");
		}
		
		userInterestRepository.delete(userInterest);
		return ServiceResult.success();
	}
	
}