package com.example.jpa.user.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.jpa.user.model.UserNoticeCount;

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
public class UserCustomRepository {

	// 1. javax.persistence.EntityManager가 필요하다. 이유 : 몰?루
	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<UserNoticeCount> findUserNoticeCount() {
		// native query 사용 - [주의]스네이크 케이스로 작성해주어야 매핑이된다.
		String sql = "SELECT u.id, u.email, u.user_name,"
				   + "	(SELECT count(*) FROM Notice n WHERE n.user_id = u.id) AS notice_count "
				   + "FROM User u";
		List<UserNoticeCount> list = entityManager.createNativeQuery(sql).getResultList();
		
		return list;
	}
	
}
