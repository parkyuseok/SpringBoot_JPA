package com.example.jpa.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.user.model.UserInput;

/**
 * @packageName : com.example.jpa.user.controller
 * @fileName    : ApiUserController.java
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

@RestController
public class ApiUserController {
	
	@PostMapping("/api/user")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
	
		List<ResponseError> responseErrorList = new ArrayList<>(); 
		
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(e -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		//return new ResponseEntity<>(HttpStatus.OK);
		return ResponseEntity.ok().build();
	}
	
}
