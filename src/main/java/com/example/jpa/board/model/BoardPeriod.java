package com.example.jpa.board.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.board.model
 * @fileName    : BoardPeriod.java
 * @author      : 박유석
 * @date        : 2022. 2. 10
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2022.02.10     박유석               최초 생성
 * </pre>
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardPeriod {

	// 문제에서 일자만 필요하므로 LocalDate를 사용하면된다.
	private LocalDate startDate;
	private LocalDate endDate;
	
}
