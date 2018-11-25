package com.hcmut.advancedprogramming.flidi.service.impl;

import com.hcmut.advancedprogramming.flidi.exception.BadRequestException;
import com.hcmut.advancedprogramming.flidi.exception.MediaNotFoundException;
import com.hcmut.advancedprogramming.flidi.exception.MediaStorageException;
import com.hcmut.advancedprogramming.flidi.exception.ResourceNotFoundException;
import com.hcmut.advancedprogramming.flidi.persistence.model.Gallery;
import com.hcmut.advancedprogramming.flidi.persistence.model.MediaGallery;
import com.hcmut.advancedprogramming.flidi.persistence.model.User;
import com.hcmut.advancedprogramming.flidi.persistence.repository.GalleryRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.MediaGalleryRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.UserRepository;
import com.hcmut.advancedprogramming.flidi.property.FileStorageProperties;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.MediaGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Transactional
@EnableConfigurationProperties(FileStorageProperties.class)
public class MediaGalleryServiceImpl implements MediaGalleryService {

    private final Path fileStorageLocation;

    @Autowired
    private MediaGalleryRepository mediaGalleryRepository;

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public MediaGalleryServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new MediaStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String store(MultipartFile file, Long galleryId, String description, UserPrincipal currentUser) {
        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new ResourceNotFoundException("Gallery", "id", galleryId));

        User user = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", currentUser.getUsername()));


        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new MediaStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String imgUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/upload/")
                    .path(fileName)
                    .toUriString();

            MediaGallery mediaGallery = new MediaGallery();
            mediaGallery.setGallery(gallery);
            mediaGallery.setUser(user);
            mediaGallery.setDescription(description);
            mediaGallery.setImgurl(imgUrl);

            mediaGalleryRepository.save(mediaGallery);

            return fileName;
        } catch (IOException ex) {
            throw new MediaStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Resource load(String name) {
        try {
            Path filePath = this.fileStorageLocation.resolve(name).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MediaNotFoundException("File not found " + name);
            }
        } catch (MalformedURLException ex) {
            throw new MediaNotFoundException("File not found " + name, ex);
        }
    }

    @Override
    public void delete(Long id, UserPrincipal currentUser) {
        MediaGallery media = mediaGalleryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MediaGallery", "id", id));

        if (media.getUser().getId() != currentUser.getId()) {
            throw new BadRequestException("Only owner of media can delete it");
        }

        mediaGalleryRepository.delete(media);
    }
}

