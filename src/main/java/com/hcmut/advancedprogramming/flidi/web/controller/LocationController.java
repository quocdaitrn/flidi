package com.hcmut.advancedprogramming.flidi.web.controller;

import com.hcmut.advancedprogramming.flidi.persistence.model.Location;
import com.hcmut.advancedprogramming.flidi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @GetMapping("/search/{key}")
    public List<Location> searchByName(@PathVariable("key") String key) {
        return locationService.searchByName(key);
    }

    @GetMapping("/province/{id}")
    public List<Location> findByProvince(@PathVariable("id") Long id) {
        return locationService.findByProvince(id);
    }
}
