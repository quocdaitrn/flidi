package com.hcmut.advancedprogramming.flidi.service;

import com.hcmut.advancedprogramming.flidi.dto.request.LocationRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.LocationResponse;
import com.hcmut.advancedprogramming.flidi.persistence.model.Location;

import java.util.List;

public interface LocationService {
    List<LocationResponse> findAll();

    List<LocationResponse> searchByName(String name);

    List<LocationResponse> findByProvince(Long id);

    Location add(LocationRequest request);
}
