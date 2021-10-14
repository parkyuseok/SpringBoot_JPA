package com.example.demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @packageName : com.example.demo
 * @fileName    : Securityconfig.java
 * @author      : 박유석
 * @date        : 2021. 10. 14
 * @version     : 1.0 
 * <pre>
 * @description : Spring Security 설정 클래스
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.10.14     박유석               최초 생성
 * </pre>
 */

// @Configurable: configuration이 작동할 수 있도록 어노테이션을 작성해준다.
// @EnableWebSecurity: Security가 작동할 수 있도록 어노테이션을 작성해준다.
@Configurable
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http 객체 설정
		http.authorizeRequests()
			.anyRequest().permitAll(); // 모든 요청에 관해서 권한을 허용
	}
	
}