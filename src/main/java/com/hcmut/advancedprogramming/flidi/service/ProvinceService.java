package com.hcmut.advancedprogramming.flidi.service;

import com.hcmut.advancedprogramming.flidi.dto.request.ProvinceRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.ProvinceResponse;
import com.hcmut.advancedprogramming.flidi.persistence.model.Province;

import java.util.List;

public interface ProvinceService {
    List<ProvinceResponse> findAll();

    ProvinceResponse getProvinceInfo(Long id);

    Province add(ProvinceRequest request);

    List<ProvinceResponse> getPopularProvinces();
}

