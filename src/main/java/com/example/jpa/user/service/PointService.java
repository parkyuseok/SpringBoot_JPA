package com.example.jpa.user.service;

import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.user.model.UserPointInput;

/**
 * @packageName : com.example.jpa.user.service
 * @fileName    : PointService.java
 * @author      : 박유석
 * @date        : 2022. 2. 11
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.12.21     박유석               최초 생성
 * </pre>
 */

public interface PointService {

	ServiceResult addPoint(String email, UserPointInput userPointInput);
	
}
