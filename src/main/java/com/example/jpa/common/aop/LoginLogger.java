package com.example.jpa.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.jpa.logs.service.LogService;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.UserLogin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @packageName : com.example.jpa.common.aop
 * @fileName    : LoginLogger.java
 * @author      : 박유석
 * @date        : 2022. 2. 14
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2022.02.14     박유석               최초 생성
 * </pre>
 */

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class LoginLogger {

	private final LogService logService;
	
	@Around("execution(* com.example.jpa..*.*Service*.*(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		
		log.info("####################################################");
		log.info("서비스 호출 전 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.info("####################################################");
		
		Object result = joinPoint.proceed();
		
		if ("login".equals(joinPoint.getSignature().getName())) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("\n");
			sb.append("함수명: " + joinPoint.getSignature().getDeclaringType() + ", " + joinPoint.getSignature().getName());
			sb.append("\n");
			sb.append("매개변수: ");
			
			Object[] args = joinPoint.getArgs();
			if (args != null && args.length > 0) {
				for(Object x : args) {
					sb.append( ((UserLogin)x).toString() );
					
					sb.append("\n");
					sb.append("리턴값: " +  ((User)result).toString());
				}
			}
			
			logService.add(sb.toString());
			
			log.info(sb.toString());
		}
		
		log.info("####################################################");
		log.info("서비스 호출 후 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.info("####################################################");
		
		return result;
	}
	
}
