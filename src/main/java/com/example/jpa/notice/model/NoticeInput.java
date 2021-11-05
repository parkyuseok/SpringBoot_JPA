package com.example.jpa.notice.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName : com.example.jpa.notice
 * @fileName    : NoticeModel.java
 * @author      : 박유석
 * @date        : 2021. 11. 2
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.02     박유석               최초 생성
 * 2021.11.02     박유석               가장 많이 사용하는 lombok 어노테이션 추가
 * </pre>
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NoticeInput {

	@NotBlank(message = "제목은 필수 항목입니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수 항목입니다.")
	private String contents;
	
}
