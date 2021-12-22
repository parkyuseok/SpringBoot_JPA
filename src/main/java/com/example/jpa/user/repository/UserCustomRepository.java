package com.example.jpa.user.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.jpa.user.model.UserLogCount;
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
		String sql = "SELECT u.id, u.email, u.user_name "
				   + ",	(SELECT count(*) FROM notice n WHERE n.user_id = u.id) AS notice_count "
				   + "FROM User u";
		
		List<UserNoticeCount> list = entityManager.createNativeQuery(sql).getResultList();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<UserLogCount> findUserLogCount() {
		// [주의] noticeLike라고 작성하면 안된다. JPA가 카멜 표기법 자체를 스네이크 케이스(_)로 생성하기 때문이다.
		String sql = "SELECT u.id, u.email, u.user_name "
				   + ", (SELECT count(*) FROM notice n WHERE n.user_id = u.id) AS notice_count "
				   + ", (SELECT count(*) FROM notice_like nl WHERE nl.user_id = u.id) AS notice_like_count "
				   + "FROM User u";
		
		List<UserLogCount> list = entityManager.createNativeQuery(sql).getResultList();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<UserLogCount> findUserLikeBest() {
		String sql = "SELECT t1.id, t1.email, t1.user_name, t1.notice_like_count "
				   + "FROM "
				   + "( "
				   + 	"SELECT u.* "
				   + 	", (SELECT count(*) FROM notice_like nl WHERE nl.user_id = u.id) AS notice_like_count "
				   + 	"FROM user u "
				   + ") t1 "
				   + "ORDER BY t1.notice_like_count desc";
		
		List<UserLogCount> list = entityManager.createNativeQuery(sql).getResultList();
		
		return list;
	}
	
}
