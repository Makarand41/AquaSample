package com.aquaindica.controller;

import com.aquaindica.Entity.Gallery;
import com.aquaindica.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GalleryController {

    private final GalleryService galleryService;

    // 🔐 Admin upload
//    @PostMapping("/api/admin/gallery")
//    public Gallery upload(
//            @RequestParam String title,
//            @RequestParam MultipartFile image
//    ) {
//        return galleryService.uploadImage(title, image);
//    }
    @PostMapping("/api/admin/gallery")
    public Gallery upload(
            @RequestParam String title,
            @RequestParam("file") MultipartFile file
    ) {
        return galleryService.uploadFile(title, file);
    }

    // 🌐 Public gallery
    @GetMapping("/api/gallery")
    public List<Gallery> getAll() {
        return galleryService.getAllImages();
    }

//    @GetMapping("/gallery/{id}")
//    public Gallery getById(@PathVariable Long id) {
//        return galleryService.getAllImages().stream()
//                .filter(g -> g.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Gallery image not found with id: " + id));
//    }
@GetMapping("/gallery/{id}")
public Gallery getById(@PathVariable Long id) {
    return galleryService.getById(id);
}


    @DeleteMapping("/api/admin/gallery/{id}")
    public void delete(@PathVariable Long id) {
        galleryService.deleteImage(id);
    }
}
