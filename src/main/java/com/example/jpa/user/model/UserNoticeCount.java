package com.example.jpa.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.user.model
 * @fileName    : UserNoticeCount.java
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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNoticeCount {

	private long id;
	private String email;
	private String userName;
	
	private long noticeCount;
	
}
