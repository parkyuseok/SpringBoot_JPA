package com.example.demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @packageName : com.example.demo
 * @fileName    : Securityconfig.java
 * @author      : ������
 * @date        : 2021. 10. 14
 * @version     : 1.0 
 * <pre>
 * @description : Spring Security ���� Ŭ����
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.10.14     ������               ���� ����
 * </pre>
 */

// @Configurable: configuration�� �۵��� �� �ֵ��� ������̼��� �ۼ����ش�.
// @EnableWebSecurity: Security�� �۵��� �� �ֵ��� ������̼��� �ۼ����ش�.
@Configurable
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http ��ü ����
		http.authorizeRequests()
			.anyRequest().permitAll(); // ��� ��û�� ���ؼ� ������ ���
	}
	
}
