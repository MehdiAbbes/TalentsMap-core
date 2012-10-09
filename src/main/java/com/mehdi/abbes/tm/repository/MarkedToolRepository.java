package com.mehdi.abbes.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.mehdi.abbes.tm.domain.MarkedTool;

@Repository
public interface MarkedToolRepository extends JpaRepository<MarkedTool, Long>,
		JpaSpecificationExecutor<MarkedTool> {
}
