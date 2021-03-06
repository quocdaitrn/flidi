package com.hcmut.advancedprogramming.flidi.persistence.repository;

import com.hcmut.advancedprogramming.flidi.persistence.model.MediaGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaGalleryRepository extends JpaRepository<MediaGallery, Long> {
    Optional<MediaGallery> findByMediaName(String mediaName);
}
