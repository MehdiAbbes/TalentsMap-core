package com.mehdi.abbes.tm.repository;

import com.mehdi.abbes.tm.domain.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaSpecificationExecutor<Profile>, JpaRepository<Profile, Long> {
}
