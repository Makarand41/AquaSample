package com.aquaindica.serviceimpl;

import com.aquaindica.Entity.Gallery;

import com.aquaindica.repository.GalleryRepository;
import com.aquaindica.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

//    @Override
//    public Gallery uploadImage(String title, MultipartFile file) {
//        try {
//            Gallery gallery = new Gallery();
//            gallery.setTitle(title);
//            gallery.setImage(file.getBytes());
//            return galleryRepository.save(gallery);
//        } catch (IOException e) {
//            throw new RuntimeException("Image upload failed");
//        }
//    }
@Override
public Gallery uploadFile(String title, MultipartFile file) {
    try {
        Gallery gallery = new Gallery();
        gallery.setTitle(title);
        gallery.setFile(file.getBytes());

        // Detect file type
        if (file.getContentType().startsWith("video")) {
            gallery.setType("video");
        } else {
            gallery.setType("image");
        }

        return galleryRepository.save(gallery);

    } catch (IOException e) {
        throw new RuntimeException("Upload failed");
    }
}
    @Override
    public Gallery getById(Long id) {
        return galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery image not found with id: " + id));
    }

    @Override
    public List<Gallery> getAllImages() {
        return galleryRepository.findAll();
    }

    @Override
    public void deleteImage(Long id) {
        galleryRepository.deleteById(id);
    }
}
