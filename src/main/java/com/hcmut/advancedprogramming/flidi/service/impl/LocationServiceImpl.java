package com.hcmut.advancedprogramming.flidi.service.impl;

import com.hcmut.advancedprogramming.flidi.persistence.model.Location;
import com.hcmut.advancedprogramming.flidi.persistence.repository.LocationRepository;
import com.hcmut.advancedprogramming.flidi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> searchByName(String name) {
        return locationRepository.search(name);
    }

    @Override
    public List<Location> findByProvince(Long id) {
        return locationRepository.findByProvinceId(id);
    }
}
