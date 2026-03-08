package com.aquaindica.controller;

import com.aquaindica.Entity.Blog;
import com.aquaindica.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/blogs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminBlogController {

    private final BlogService blogService;

    @PostMapping(consumes = "multipart/form-data")
    public Blog createBlog(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String author,
            @RequestParam MultipartFile coverImage
    ) throws IOException {

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setAuthor(author);
        blog.setCoverImage(coverImage.getBytes());
        blog.setCreatedAt(java.time.LocalDateTime.now());

        return blogService.createBlog(blog);
    }

    @GetMapping("/getBlogs")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
    }
}
