package com.aquaindica.service;

import com.aquaindica.Entity.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {
   // Gallery uploadImage(String title, MultipartFile file);
   public Gallery uploadFile(String title, MultipartFile file);
    List<Gallery> getAllImages();
    Gallery getById(Long id);

    void deleteImage(Long id);
}
