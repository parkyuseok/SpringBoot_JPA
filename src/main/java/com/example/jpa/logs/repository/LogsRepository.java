package com.example.jpa.logs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.logs.entity.Logs;

/**
 * @packageName : com.example.jpa.logs.repository
 * @fileName    : LogsRepository.java
 * @author      : 박유석
 * @date        : 2022. 2. 14
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2022.02.14     박유석               최초 생성
 * </pre>
 */

@Repository
public interface LogsRepository extends JpaRepository<Logs, Long> {

}
