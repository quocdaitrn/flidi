package com.hcmut.advancedprogramming.flidi.service.impl;

import com.hcmut.advancedprogramming.flidi.dto.request.ProvinceRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.ProvinceResponse;
import com.hcmut.advancedprogramming.flidi.exception.ResourceNotFoundException;
import com.hcmut.advancedprogramming.flidi.persistence.model.Province;
import com.hcmut.advancedprogramming.flidi.persistence.repository.ProvinceRepository;
import com.hcmut.advancedprogramming.flidi.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<ProvinceResponse> findAll() {
        List<ProvinceResponse> provinceResponses = new ArrayList<>();
        List<Province> provinces = provinceRepository.findAll();
        for (Province p : provinces) {
            ProvinceResponse provinceResponse = new ProvinceResponse();
            provinceResponse.setId(p.getId());
            provinceResponse.setName(p.getProvinceName());
            provinceResponse.setLongitude(p.getLongitude());
            provinceResponse.setLatitude(p.getLatitude());
            provinceResponse.setPopular(p.getPopular());
            provinceResponse.setStatus(p.getStatus());
            provinceResponse.setLocations(p.getLocations().size());

            provinceResponses.add(provinceResponse);
        }

        return provinceResponses;
    }

    @Override
    public ProvinceResponse getProvinceInfo(Long id) {
        ProvinceResponse provinceResponse = new ProvinceResponse();

        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Province", "id", id));

        provinceResponse.setId(province.getId());
        provinceResponse.setName(province.getProvinceName());
        provinceResponse.setLongitude(province.getLongitude());
        provinceResponse.setLatitude(province.getLatitude());
        provinceResponse.setPopular(province.getPopular());
        provinceResponse.setStatus(province.getStatus());
        provinceResponse.setLocations(province.getLocations().size());

        return provinceResponse;
    }

    @Override
    public Province add(ProvinceRequest request) {
        Province province = new Province();
        province.setProvinceName(request.getName());
        province.setPopular(request.getPopular());
        province.setLongitude(request.getLongitude());
        province.setLatitude(request.getLatitude());
        province.setStatus(request.getStatus());

        return provinceRepository.save(province);
    }

    @Override
    public List<ProvinceResponse> getPopularProvinces() {
        List<ProvinceResponse> provinceResponses = new ArrayList<>();
        List<Province> provinces = provinceRepository.getPopulars();
        for (Province p : provinces) {
            ProvinceResponse provinceResponse = new ProvinceResponse();
            provinceResponse.setId(p.getId());
            provinceResponse.setName(p.getProvinceName());
            provinceResponse.setLongitude(p.getLongitude());
            provinceResponse.setLatitude(p.getLatitude());
            provinceResponse.setPopular(p.getPopular());
            provinceResponse.setStatus(p.getStatus());
            provinceResponse.setLocations(p.getLocations().size());

            provinceResponses.add(provinceResponse);
        }

        return provinceResponses;
    }
}
