package com.mehdi.abbes.tm.repository;

import com.mehdi.abbes.tm.domain.Concept;
import com.mehdi.abbes.tm.domain.ConceptId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptRepository extends JpaRepository<Concept, ConceptId>, JpaSpecificationExecutor<Concept> {
}
