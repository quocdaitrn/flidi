package com.hcmut.advancedprogramming.flidi.web.controller;

import com.google.common.base.Joiner;
import com.hcmut.advancedprogramming.flidi.dto.request.LocationRequest;
import com.hcmut.advancedprogramming.flidi.dto.request.ProvinceRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.ApiResponse;
import com.hcmut.advancedprogramming.flidi.dto.response.LocationResponse;
import com.hcmut.advancedprogramming.flidi.persistence.model.Location;
import com.hcmut.advancedprogramming.flidi.persistence.model.Province;
import com.hcmut.advancedprogramming.flidi.persistence.repository.LocationRepository;
import com.hcmut.advancedprogramming.flidi.persistence.specification.LocationSpecificationBuilder;
import com.hcmut.advancedprogramming.flidi.persistence.specification.SearchOperation;
import com.hcmut.advancedprogramming.flidi.security.CurrentUser;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.LocationService;
import com.hcmut.advancedprogramming.flidi.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @Autowired
    LocationRepository locationRepository;

    @GetMapping
    public List<LocationResponse> findAll() {
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public LocationResponse getLocationById(@PathVariable("id") Long id) {
        return locationService.findById(id);
    }

    @GetMapping("/search")
    public List<LocationResponse> search(@RequestParam(value = "search") String search) {
        LocationSpecificationBuilder builder = new LocationSpecificationBuilder();
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);

        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<Location> spec = builder.build();
        return locationService.search(spec);
    }

    @GetMapping("/province/{id}")
    public List<LocationResponse> findByProvince(@PathVariable("id") Long id) {
        return locationService.findByProvince(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addLocation(@Valid @RequestBody LocationRequest request, @CurrentUser UserPrincipal currentUser) {
        Location location = locationService.add(request);

        URI path = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{locationId}")
                .buildAndExpand(location.getId()).toUri();

        return ResponseEntity.created(path)
                .body(new ApiResponse(true, "Location added successfully"));
    }
}
