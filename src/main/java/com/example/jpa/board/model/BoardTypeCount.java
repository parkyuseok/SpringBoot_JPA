/**
 * 
 */
package com.example.jpa.board.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.board.model
 * @fileName    : BoardTypeCount.java
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
public class BoardTypeCount {

	// BoardTypeCustomRepository - 62~66번라인에서 사용
	public BoardTypeCount(Object[] arrObj) {
		this.id = ((BigInteger)arrObj[0]).longValue();
		this.boardName = (String)arrObj[1];
		this.regDate = ((Timestamp)arrObj[2]).toLocalDateTime();
		this.usingYn = (Boolean)arrObj[3];
		this.boardCount = ((BigInteger)arrObj[4]).longValue();
	}
	// BoardTypeCustomRepository -76번라인에서 사용
	public BoardTypeCount(BigInteger id, String boardName, Timestamp regDate, Boolean usingYn, BigInteger boardCount) {
		this.id = id.longValue();
		this.boardName = boardName;
		this.regDate = regDate.toLocalDateTime();
		this.usingYn = usingYn;
		this.boardCount = boardCount.longValue();
	}

	private long id;
	private String boardName;
	private LocalDateTime regDate;
	private boolean usingYn;
	private long boardCount;
	
}
