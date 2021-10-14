package com.example.jpa.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @packageName : com.example.demo
 * @fileName    : FirstController.java
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

@Controller
public class FirstController {

	@RequestMapping(value = "/first-url", method = RequestMethod.GET)
	public void first() {
		
	}
	
	// Spring은 기본적으로 View Page를 return 하는데
	// 그냥 문자열을 리턴하고 싶을 때 ResponseBody 어노테이션을 작성해준다. 
	@ResponseBody
	@RequestMapping(value = "/helloworld")
	public String helloworld() {
		
		return "hello world";
	}
}