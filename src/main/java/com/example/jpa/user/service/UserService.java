package com.example.jpa.user.service;

import java.util.List;

import com.example.jpa.user.entity.User;
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
	
}
