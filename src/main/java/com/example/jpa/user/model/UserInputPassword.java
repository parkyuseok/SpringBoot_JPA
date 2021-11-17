/**
 * 
 */
package com.example.jpa.user.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.user.model
 * @fileName    : UserInputPassword.java
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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserInputPassword {

	@NotBlank(message = "현재 비밀번호는 필수 항목입니다.")
	private String password;
	
	@Size(min = 4, max = 20, message = "신규 비밀번호는 4-20 사이의 길이로 입력해 주세요.")
	private String newPassword;
	
}
