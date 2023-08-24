package blog.blogbackend.controller;

import blog.blogbackend.Repository.BlogRepository;
import blog.blogbackend.model.Blog;
import blog.blogbackend.service.BlogService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class BlogController {

    @Resource
    private BlogService blogService;
    @Resource
    private BlogRepository blogRepository;

    @GetMapping("/blogs")
    @RateLimiter(name="blogs")
    public List<Blog> getAllBlogs() {
        return this.blogService.getAllBlogs();
    }

    @PostMapping("/blog")
    public Blog createBlog(@Valid @RequestBody Blog blog) {
        return this.blogService.createBlog(blog);
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable("id") long id) {
        this.blogRepository.deleteById(id);
        return ResponseEntity.ok("Delete successful!");
    }

    @ExceptionHandler({ RequestNotPermitted.class })
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void handleRequestNotPermitted() {
    }
}
