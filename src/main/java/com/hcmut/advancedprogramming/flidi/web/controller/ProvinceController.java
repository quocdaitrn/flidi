package com.hcmut.advancedprogramming.flidi.web.controller;

import com.hcmut.advancedprogramming.flidi.dto.request.ProvinceRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.ApiResponse;
import com.hcmut.advancedprogramming.flidi.dto.response.ProvinceResponse;
import com.hcmut.advancedprogramming.flidi.persistence.model.Comment;
import com.hcmut.advancedprogramming.flidi.persistence.model.Province;
import com.hcmut.advancedprogramming.flidi.security.CurrentUser;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public List<ProvinceResponse> findAll() {
        return provinceService.findAll();
    }

    @GetMapping("/{id}")
    public ProvinceResponse findById(@PathVariable Long id) {
        return provinceService.getProvinceInfo(id);
    }

    @GetMapping("/popular")
    public List<ProvinceResponse> findPopularProvinces() {
        return provinceService.getPopularProvinces();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProvince(@Valid @RequestBody ProvinceRequest request, @CurrentUser UserPrincipal currentUser) {
        Province province = provinceService.add(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{provinceId}")
                .buildAndExpand(province.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Province added successfully"));
    }
}
