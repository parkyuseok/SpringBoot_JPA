package com.example.jpa.notice.model;

import java.util.List;

import lombok.Data;

/**
 * @packageName : com.example.jpa.notice.model
 * @fileName    : NoticeDeleteInput.java
 * @author      : 박유석
 * @date        : 2021. 11. 3
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.03     박유석               최초 생성
 * </pre>
 */

@Data
public class NoticeDeleteInput {
	private List<Long> idList;
}
