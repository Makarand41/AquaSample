package com.aquaindica.service;

import com.aquaindica.Entity.Blog;

import java.util.List;

public interface BlogService {
    Blog createBlog(Blog blog);
    List<Blog> getAllBlogs();
    Blog getBlogById(Long id);
    void deleteBlog(Long id);
}
