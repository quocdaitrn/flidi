package com.hcmut.advancedprogramming.flidi.service;

import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface MediaGalleryService {
    String store(MultipartFile file, Long galleryId, String description, UserPrincipal user);

    Resource load(String name);

    void delete(Long id, UserPrincipal currentUser);
}
