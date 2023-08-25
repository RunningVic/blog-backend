package blog.blogbackend.service.implementation;

import blog.blogbackend.Repository.BlogRepository;
import blog.blogbackend.dto.BlogDto;
import blog.blogbackend.entity.Blog;
import blog.blogbackend.exception.ResourceNotFoundException;
import blog.blogbackend.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        // convert DTO to Entity
        Blog blog = new Blog();
        Date now = new Date();
        blog.setCreatedAt(now);
        blog.setLastModifiedAt(now);
        blog.setTitle(blogDto.getTitle());

        Blog newBlog = this.blogRepository.save(blog);
        // Convert Entity to DTO
        BlogDto response = mapToDto(newBlog);
        return response;
    }

    @Override
    public List<BlogDto> getAllBlogs() {
        List<Blog> blogs = this.blogRepository.findAll();
        List<BlogDto> response = blogs.stream().map(blog -> mapToDto(blog)).collect(Collectors.toList());
        return response;
    }

    @Override
    public BlogDto getBlogById(long id) {
        Blog blog = this.blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog", "id", id));
        return mapToDto(blog);
    }

    @Override
    public BlogDto updateBlogById(long id, BlogDto blogDto) {
        Blog blog = this.blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog", "id", id));
        blog.setTitle(blogDto.getTitle());
        Blog updatedBlog = this.blogRepository.save(blog);
        return mapToDto(updatedBlog);
    }

    @Override
    public void deleteBlogById(long id) {
        Blog blog = this.blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog", "id", id));
        this.blogRepository.delete(blog);
    }

    private BlogDto mapToDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        blogDto.setTitle(blog.getTitle());
        blogDto.setId(blog.getId());
        return blogDto;
    }
}
