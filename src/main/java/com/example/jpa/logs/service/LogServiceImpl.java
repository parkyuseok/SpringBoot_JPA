package com.example.jpa.logs.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.jpa.logs.entity.Logs;
import com.example.jpa.logs.repository.LogsRepository;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.logs.service
 * @fileName    : LogServiceImpl.java
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

@RequiredArgsConstructor
@Service
public class LogServiceImpl implements LogService {

	private final LogsRepository logsRepository;
	
	@Override
	public void add(String text) {
		
		logsRepository.save(Logs.builder()
				.text(text)
				.regDate(LocalDateTime.now())
				.build());
		
	}

}
