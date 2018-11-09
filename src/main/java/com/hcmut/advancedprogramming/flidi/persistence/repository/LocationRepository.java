package com.hcmut.advancedprogramming.flidi.persistence.repository;

import com.hcmut.advancedprogramming.flidi.persistence.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByLocationName(String name);

    List<Location> findByProvinceId(Long id);

    @Query("SELECT l FROM Location l WHERE l.locationName LIKE CONCAT('%',UPPER(:name),'%')")
    List<Location> search(@Param("name") String name);
}
