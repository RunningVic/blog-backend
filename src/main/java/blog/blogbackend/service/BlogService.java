package blog.blogbackend.service;

import blog.blogbackend.dto.BlogDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogService {
    BlogDto createBlog(BlogDto blogDto);

    List<BlogDto> getAllBlogs();

    BlogDto getBlogById(long id);

    BlogDto updateBlogById(long id, BlogDto blogDto);

    void deleteBlogById(long id);
}
