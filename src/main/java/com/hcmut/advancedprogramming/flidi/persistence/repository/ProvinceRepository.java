package com.hcmut.advancedprogramming.flidi.persistence.repository;

import com.hcmut.advancedprogramming.flidi.persistence.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
}
