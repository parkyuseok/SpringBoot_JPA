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
 * @fileName    : BoardTypeUsing.java
 * @author      : 박유석
 * @date        : 2022. 2. 9
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2022.02.09     박유석               최초 생성
 * </pre>
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardTypeUsing {

	private boolean usingYn;
	
}
