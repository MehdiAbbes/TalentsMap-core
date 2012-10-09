package com.mehdi.abbes.tm.repository;

import com.mehdi.abbes.tm.domain.Tool;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaSpecificationExecutor<Tool>, JpaRepository<Tool, Long> {
}
