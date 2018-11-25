package com.hcmut.advancedprogramming.flidi.persistence.repository;

import com.hcmut.advancedprogramming.flidi.persistence.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
