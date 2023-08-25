package blog.blogbackend.controller;

import blog.blogbackend.Repository.BlogRepository;
import blog.blogbackend.dto.BlogDto;
import blog.blogbackend.entity.Blog;
import blog.blogbackend.service.BlogService;
import blog.blogbackend.service.implementation.BlogServiceImpl;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    @RateLimiter(name="blogs")
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        List<BlogDto> response = this.blogService.getAllBlogs();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable("id") long id) {
        BlogDto response = this.blogService.getBlogById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@Valid @RequestBody BlogDto blogDto) {
        BlogDto response = this.blogService.createBlog(blogDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlogById(@PathVariable("id") long id, @RequestBody BlogDto blogDto) {
        BlogDto response = this.blogService.updateBlogById(id, blogDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogById(@PathVariable("id") long id) {
        this.blogService.deleteBlogById(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @ExceptionHandler({ RequestNotPermitted.class })
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void handleRequestNotPermitted() {
    }
}
