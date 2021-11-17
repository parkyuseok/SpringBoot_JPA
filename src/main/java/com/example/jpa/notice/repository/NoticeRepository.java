package com.example.jpa.notice.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.user.entity.User;

/**
 * @packageName : com.example.jpa.notice.repository
 * @fileName    : NoticeRepository.java
 * @author      : 박유석
 * @date        : 2021. 11. 2
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.02     박유석               최초 생성
 * </pre>
 */

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> { //<Entity, PK Type>

	// 데이터가 없을 수 있으니 Optional로 처리를 해서 리턴한다.
	Optional<List<Notice>> findByIdIn(List<Long> idList);
	
	// 제목동일, 내용동일, 등록시간이 체크시간보다 크다.
	// Optional<List<Notice>> findByTitleAndContentsAndRegDateIsGreaterThanEqual(String title, String contents, LocalDateTime regDate);
	int countByTitleAndContentsAndRegDateIsGreaterThanEqual(String title, String contents, LocalDateTime regDate);
	
	List<Notice> findByUser(User user);
}
