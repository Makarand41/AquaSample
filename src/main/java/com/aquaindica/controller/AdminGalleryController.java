//package com.aquaindica.controller;
//
//import com.aquaindica.Entity.Gallery;
//import com.aquaindica.service.GalleryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/admin/gallery")
//@CrossOrigin(origins = "http://localhost:4200")
//public class AdminGalleryController {
//
//    private final GalleryService galleryService;
//
//    public AdminGalleryController(GalleryService galleryService) {
//        this.galleryService = galleryService;
//    }
//
//    @PostMapping
//    public Gallery addImage(@RequestBody Gallery gallery) {
//        return galleryService.saveImage(gallery);
//    }
//
//    @GetMapping
//    public List<Gallery> getGallery() {
//        return galleryService.getAllImages();
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteImage(@PathVariable Long id) {
//        galleryService.deleteImage(id);
//    }
//}
