package com.example.jpa.notice.model;

import java.time.LocalDateTime;

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

@AllArgsConstructor //전체 내용에 대한 생성자
@NoArgsConstructor  //생성자 부분에 대한 이슈가 발생할 수 있으므로 default 생성자를 만들어준다.
@Builder            //데이터를 입력할 때 Builder pattern을 이용, static한 클래스를 이용해서 편하게 사용할 수 있는 형태
@Data               //getter setter를 편하게 사용할 수 있다.
public class NoticeModel {

	// ID, 제목, 내용, 등록일(작성일)
	private int id;
	private String title;
	private String contents;
	private LocalDateTime regDate; // 요즘 여러가지 이유로 Date type은 사용하지 않는다.
	
	// private이므로 외부에서 사용하기 위해서는 getter, setter가 필요하다.
}
