package com.example.jpa.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.entity.UserPoint;
import com.example.jpa.user.model.UserPointInput;
import com.example.jpa.user.repository.UserPointRepository;
import com.example.jpa.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.user.service
 * @fileName    : PointServiceImpl.java
 * @author      : 박유석
 * @date        : 2022. 2. 11
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2022.02.11     박유석               최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@Service
public class PointServiceImpl implements PointService {
	
	private final UserRepository userRepository;
	private final UserPointRepository userPointRepository;
	
	@Override
	public ServiceResult addPoint(String email, @RequestBody UserPointInput userPointInput) {
		
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
		}
		User user = optionalUser.get();
		
		userPointRepository.save(UserPoint.builder()
				.user(user)
				.userPointType(userPointInput.getUserPointType())
				.point(userPointInput.getUserPointType().getValue())
				.build());
		
		return ServiceResult.success();
	}

}
