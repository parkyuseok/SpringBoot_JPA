package com.example.jpa.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.user.model
 * @fileName    : UserInput.java
 * @author      : 박유석
 * @date        : 2021. 11. 5
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.05     박유석               최초 생성
 * </pre>
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserInput {

	@Email(message = "이메일 형식에 맞게 입력해 주세요.")
	@NotBlank(message = "이메일은 필수 항목 입니다.")
	private String email;
	
	@NotBlank(message = "이름은 필수 항목 입니다.")
	private String userName;
	
	@Size(min = 4, message = "비밀번호는 4자 이상 입력해야 합니다.")
	@NotBlank(message = "비밀번호는 필수 항목 입니다.")
	private String password;
	
	@Size(max = 20, message = "연락처는 최대 20자까지 입력해야 합니다.")
	@NotBlank(message = "연락처는 필수 항목 입니다.")
	private String phone;
	
}
