package com.hcmut.advancedprogramming.flidi.persistence.repository;

import com.hcmut.advancedprogramming.flidi.persistence.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
