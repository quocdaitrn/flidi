package com.hcmut.advancedprogramming.flidi.service.impl;

import com.hcmut.advancedprogramming.flidi.dto.request.LocationRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.LocationResponse;
import com.hcmut.advancedprogramming.flidi.dto.response.ProvinceInLocationResponse;
import com.hcmut.advancedprogramming.flidi.exception.ResourceNotFoundException;
import com.hcmut.advancedprogramming.flidi.persistence.model.Location;
import com.hcmut.advancedprogramming.flidi.persistence.model.Province;
import com.hcmut.advancedprogramming.flidi.persistence.repository.LocationRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.ProvinceRepository;
import com.hcmut.advancedprogramming.flidi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<LocationResponse> findAll() {
        List<LocationResponse> locationResponses = new ArrayList<>();

        List<Location> locations = locationRepository.findAll();
        for (Location l : locations) {
            locationResponses.add(locationMapping(l));
        }

        return locationResponses;
    }

    @Override
    public List<LocationResponse> searchByName(String name) {
        List<LocationResponse> locationResponses = new ArrayList<>();

        List<Location> locations = locationRepository.search(name);
        for (Location l : locations) {
            locationResponses.add(locationMapping(l));
        }

        return locationResponses;
    }

    @Override
    public List<LocationResponse> findByProvince(Long id) {
        List<LocationResponse> locationResponses = new ArrayList<>();

        List<Location> locations = locationRepository.findByProvinceId(id);
        for (Location l : locations) {
            locationResponses.add(locationMapping(l));
        }

        return locationResponses;
    }

    @Override
    public Location add(LocationRequest request) {
        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new ResourceNotFoundException("Province", "id", request.getProvinceId()));

        Location location = new Location();

        location.setLocationName(request.getName());
        location.setAddress(request.getAddress());
        location.setDescription(request.getDescription());
        location.setDetail(request.getDetail());
        location.setImage(request.getImage());
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        location.setStatus(request.getStatus());
        location.setProvince(province);

        return locationRepository.save(location);
    }

    private LocationResponse locationMapping(Location l) {
        LocationResponse locationResponse = new LocationResponse();

        locationResponse.setName(l.getLocationName());
        locationResponse.setAddress(l.getAddress());
        locationResponse.setDescription(l.getDescription());
        locationResponse.setDetail(l.getDetail());
        locationResponse.setId(l.getId());
        locationResponse.setImage(l.getImage());
        locationResponse.setLatitude(l.getLatitude());
        locationResponse.setLongitude(l.getLongitude());
        locationResponse.setProvince(provinceInLocationMapping(l.getProvince()));
        locationResponse.setStatus(l.getStatus());

        return locationResponse;
    }

    private ProvinceInLocationResponse provinceInLocationMapping(Province province) {
        ProvinceInLocationResponse provinceInLocationResponse = new ProvinceInLocationResponse();

        provinceInLocationResponse.setId(province.getId());
        provinceInLocationResponse.setName(province.getProvinceName());
        provinceInLocationResponse.setLatitude(province.getLatitude());
        provinceInLocationResponse.setLongitude(province.getLongitude());
        provinceInLocationResponse.setPopular(province.getPopular());
        provinceInLocationResponse.setStatus(province.getStatus());

        return provinceInLocationResponse;
    }
}
