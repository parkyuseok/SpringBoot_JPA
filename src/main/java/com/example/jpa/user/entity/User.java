package com.example.jpa.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.jpa.user.model.UserStatus;

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
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String email;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String phone;
	
	@Column
	private LocalDateTime regDate;
	
	@Column
	private LocalDateTime updateDate;
	
	@Column
	private UserStatus status;
	
}
