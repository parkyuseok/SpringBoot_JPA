/**
 * 
 */
package com.example.jpa.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.notice.entity.Notice;

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

}
