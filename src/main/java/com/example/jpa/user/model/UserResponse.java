/**
 * 
 */
package com.example.jpa.user.model;

import com.example.jpa.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.user.model
 * @fileName    : UserResponse.java
 * @author      : 박유석
 * @date        : 2021. 11. 16
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.16     박유석               최초 생성
 * </pre>
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse {
	
	private long id;
	private String email;
	private String userName;
	private String phone;
	
//	public UserResponse(User user) {
//		this.id = user.getId();
//		this.email = user.getEmail();
//		this.userName = user.getUserName();
//		this.phone = user.getPhone();
//	}

	public static UserResponse of(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.email(user.getEmail())
				.userName(user.getUserName())
				.phone(user.getPhone())
				.build();
	}

}
