package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @packageName : com.example.demo
 * @fileName    : SecondController.java
 * @author      : 박유석
 * @date        : 2021. 10. 14
 * @version     : 1.0 
 * <pre>
 * @description : 주소요청에 대한 연습을 하는 클래스
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.10.14     박유석               최초 생성
 * </pre>
 */

// 1. RequestBody를 사용하지 않고 문자열을 리턴하려면?
// @RestController: viewResolver가 아니라 String 형태로 리턴한다.
@RestController
public class SecondController {

	@RequestMapping(value = "/hello-spring", method = RequestMethod.GET)
	public String helloSpring() {
		
		return "hello Spring";
	}
	
}