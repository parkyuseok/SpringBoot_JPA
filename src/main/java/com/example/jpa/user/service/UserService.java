package com.example.jpa.user.service;

import java.util.List;

import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.UserLogCount;
import com.example.jpa.user.model.UserNoticeCount;
import com.example.jpa.user.model.UserSummary;

/**
 * @packageName : com.example.jpa.user.service
 * @fileName    : UserService.java
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

public interface UserService {

	UserSummary getUserStatusCount();

	List<User> getTodayUsers();

	List<UserNoticeCount> getUserNoticeCount();

	List<UserLogCount> getUserLogCount();

	/**
	 * 좋아요를 가장 많이 한 사용자 목록 리턴
	 * [참고]
	 * 이름으로 의미가 불분명할 때 주석을 달아준다.
	 */
	List<UserLogCount> getUserLikeBest();

	/**
	 * 관심사용자 등록
	 */
	ServiceResult addInterestUser(String email, Long id);

	/**
	 * 관심사용자 삭제
	 */
	ServiceResult removeInterestUser(String email, Long id);
	
}
