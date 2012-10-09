package com.mehdi.abbes.tm.repository;

import com.mehdi.abbes.tm.domain.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaSpecificationExecutor<Category>, JpaRepository<Category, Long> {
}
