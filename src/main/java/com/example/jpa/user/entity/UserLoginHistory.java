/**
 * 
 */
package com.example.jpa.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.user.entity
 * @fileName    : UserLoginHistory.java
 * @author      : 박유석
 * @date        : 2021. 12. 20
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.12.20     박유석               최초 생성
 * </pre>
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserLoginHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private long userId;
	
	@Column
	private String email;
	
	@Column
	private String userName;
	
	@Column
	private LocalDateTime loginDate;
	
	@Column
	private String ipAddr;
	
}
