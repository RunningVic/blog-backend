package blog.blogbackend.controller;

import blog.blogbackend.model.Blog;
import blog.blogbackend.model.Tag;
import blog.blogbackend.service.BlogService;
import blog.blogbackend.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TagController {
    @Resource
    private TagService tagService;
    @Resource
    private BlogService blogService;

    @PostMapping("/tag")
    public ResponseEntity<Tag> createTag(@Valid @RequestBody Tag tag) {
        return new ResponseEntity<>(this.tagService.createTag(tag), HttpStatus.OK);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") long id) {
        Optional<Tag> response = this.tagService.getById(id);
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }
}
