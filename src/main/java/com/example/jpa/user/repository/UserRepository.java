package com.example.jpa.user.repository;

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

	int CountByEmail(String email);
}
