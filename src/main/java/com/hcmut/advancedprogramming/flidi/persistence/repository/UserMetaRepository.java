package com.hcmut.advancedprogramming.flidi.persistence.repository;

import com.hcmut.advancedprogramming.flidi.persistence.model.UserMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMetaRepository extends JpaRepository<UserMeta, Long> {
}
