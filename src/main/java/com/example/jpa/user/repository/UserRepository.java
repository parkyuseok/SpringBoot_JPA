package com.example.jpa.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.user.entity.User;

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
}
