package com.example.jpa.board.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.example.jpa.board.model.BoardTypeCount;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.user.repository
 * @fileName    : UserRepository.java
 * @author      : 박유석
 * @date        : 2021. 11. 16
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.16     박유석               최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@Repository
public class BoardTypeCustomRepository {

	private final EntityManager entityManager;

	public List<BoardTypeCount> getBoardTypeCount() {
		
		String sql = "SELECT bt.id, bt.board_name, bt.reg_date, bt.using_yn "
				   + ", (SELECT COUNT(*) FROM board b WHERE b.board_type_id = bt.id) AS board_count "
				   + "FROM board_type bt";
		
		//List<BoardTypeCount> list = entityManager.createNativeQuery(sql).getResultList();
		/** 다음과 같은 형식의 데이터를 key: value 형식으로 바꿔야함..
		 * [
				[
					1,
					"게시판1",
					"2021-12-22T03:26:43.000+00:00",
					true,
					2
				],
				[
					2,
					"게시판2",
					"2021-12-22T03:26:43.000+00:00",
					true,
					0
				]
			]
		 */
		// 첫번째 방법 stream API를 활용하여 생성자에 전달해서 매핑 시켜줌 -> 번거로움
		/*
		List<Object[]> result = entityManager.createNativeQuery(sql).getResultList();
		List<BoardTypeCount> resultList = result.stream().map(e -> new BoardTypeCount(e))
				.collect(Collectors.toList());
		*/
		
		// 두번째 방법 Entity에 @SqlResultSetMapping 어노테이션에 매핑정보를 작성해준다.
		// 가독성이 떨어지고 Entity에 있을 이유가 없어보여서 사용하지 x
		
		// 세번째 방법 - 라이브러리 활용(maven repository에 있는 QLRM(ch.simas.qlrm))
		// 라이브러리의 함수를 호출하면 위의 작업을 해준다.(생성자는 직접 만들어야함)
		Query nativeQuery = entityManager.createNativeQuery(sql);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		List<BoardTypeCount> resultList = jpaResultMapper.list(nativeQuery, BoardTypeCount.class);
		
		return resultList;
	}
	
}
