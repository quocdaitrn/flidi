package com.hcmut.advancedprogramming.flidi.service;

import com.hcmut.advancedprogramming.flidi.persistence.model.Location;

import java.util.List;

public interface LocationService {
    List<Location> findAll();

    List<Location> searchByName(String name);

    List<Location> findByProvince(Long id);
}
