package com.example.jpa.user.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.UserStatus;

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

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	int countByEmail(String email);
	
	Optional<User> findByIdAndPassword(long id, String password);
	
	Optional<User> findByUserNameAndPhone(String userName, String phone);
	
	Optional<User> findByEmail(String email);
	
	/**
	 * email like '%' || email || '%'     - oracle
	 * email like concat('%', email, '%') - mysql
	 * @param email
	 * @param phone
	 * @param userName
	 * @return
	 */
	List<User> findByEmailContainsOrPhoneContainsOrUserNameContains(String email, String phone, String userName);

	long countByStatus(UserStatus userStatus);
	
	// User findByRegDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	/* JPQL(Java Persistence Query Language)
	 * - 테이블이 아닌 엔티티 객체를 대상으로 검색하는 객체지향 쿼리
	 * - SQL을 추상화해서 특정 데이터베이스 SQL에 의존하지 않음
	 * - JPA는 JPQL을 분석한 후 적절한 SQL을 만들어서 데이터베이스를 조회
	 * - 방언(Dialect)만 변경하면 JPQL을 수정하지 않고 자연스럽게 DB 변경 가능
	 * [주의]
	 * Entity Alias를 반드시 사용해서 작성해야한다. (아래 예시에서는 u)
	 */
	@Query("select u from User u where u.regDate between :startDate and :endDate")
	List<User> findToday(@Param(value = "startDate") LocalDateTime startDate, @Param(value = "endDate") LocalDateTime endDate);
}
