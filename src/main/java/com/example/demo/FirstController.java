package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @packageName : com.example.demo
 * @fileName    : FirstController.java
 * @author      : ������
 * @date        : 2021. 10. 14
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.10.14     ������               ���� ����
 * </pre>
 */

@Controller
public class FirstController {

	@RequestMapping(value = "/first-url", method = RequestMethod.GET)
	public void first() {
		
	}
	
}
