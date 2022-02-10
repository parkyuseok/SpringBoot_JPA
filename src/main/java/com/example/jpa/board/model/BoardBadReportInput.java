/**
 * 
 */
package com.example.jpa.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.board.model
 * @fileName    : BoardBadReportInput.java
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
public class BoardBadReportInput {
	private String comments;
}
