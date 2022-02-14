package com.example.jpa.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.jpa.logs.service.LogService;

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
public class BoardLogger {

	private final LogService logService;
	
	@Around("execution(* com.example.jpa..*.*Controller*.detail(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		
		log.info("####################################################");
		log.info("컨트롤러 detail 호출 전 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.info("####################################################");
		
		Object result = joinPoint.proceed();
		
		if (joinPoint.getSignature().getDeclaringTypeName().contains("ApiBoardController")
				&& "detail".equals(joinPoint.getSignature().getName())) {
			
			StringBuilder sb = new StringBuilder();
			
			Object[] args = joinPoint.getArgs();
			for(Object x : args) {
				sb.append(x.toString());
			}
			sb.append("결과: ");
			sb.append(result.toString());
			
			logService.add(sb.toString());
			log.info(sb.toString());
		}
		
		log.info("####################################################");
		log.info("컨트롤러 detail 호출 후 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.info("####################################################");
		
		return result;
	}
	
}
