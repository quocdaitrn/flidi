package com.hcmut.advancedprogramming.flidi.web.controller;

import com.hcmut.advancedprogramming.flidi.dto.response.ApiResponse;
import com.hcmut.advancedprogramming.flidi.dto.response.MediaUploadResponse;
import com.hcmut.advancedprogramming.flidi.security.CurrentUser;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.MediaGalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medias")
public class MediaController {

    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

    @Autowired
    private MediaGalleryService mediaGalleryService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public MediaUploadResponse uploadMedia(@RequestParam("media") MultipartFile media, @RequestParam("gallery_id") Long galleryId,
                                           @RequestParam("description") String description, @CurrentUser UserPrincipal currentUser) {
        String fileName = mediaGalleryService.store(media, galleryId, description, currentUser);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/medias/")
                .path(fileName)
                .toUriString();

        return new MediaUploadResponse(fileName, fileDownloadUri,
                media.getContentType(), media.getSize());
    }

    @PostMapping("/multiple")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<MediaUploadResponse> uploadMultipleMedias(@RequestParam("medias") MultipartFile[] medias, @RequestParam("gallery_id") Long galleryId,
                                                          @RequestParam("description") String description, @CurrentUser UserPrincipal currentUser) {
        return Arrays.asList(medias)
                .stream()
                .map(file -> uploadMedia(file, galleryId, description, currentUser))
                .collect(Collectors.toList());
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadMedia(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = mediaGalleryService.load(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteMedia(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        mediaGalleryService.delete(id, currentUser);

        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Media deleted successfully"));
    }
}