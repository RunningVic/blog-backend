package blog.blogbackend.controller;

import blog.blogbackend.entity.Category;
import blog.blogbackend.service.BlogService;
import blog.blogbackend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CategoryController {
    @Resource
    private CategoryService tagService;
    @Resource
    private BlogService blogService;

    @PostMapping("/tag")
    public ResponseEntity<Category> createTag(@Valid @RequestBody Category tag) {
        return new ResponseEntity<>(this.tagService.createTag(tag), HttpStatus.OK);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<Category> getTagById(@PathVariable("id") long id) {
        Optional<Category> response = this.tagService.getById(id);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }
}
