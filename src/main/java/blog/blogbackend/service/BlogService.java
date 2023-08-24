package blog.blogbackend.service;

import blog.blogbackend.Repository.BlogRepository;
import blog.blogbackend.model.Blog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Resource
    private BlogRepository blogRepository;
    public Blog createBlog(Blog blog) {
        Date now = new Date();
        blog.setCreatedAt(now);
        blog.setLastModifiedAt(now);
        Blog newBlog = this.blogRepository.save(blog);
        return newBlog;
    }

    public List<Blog> getAllBlogs() {
        return this.blogRepository.findAll();
    }
}
