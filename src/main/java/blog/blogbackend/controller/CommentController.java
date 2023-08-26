package blog.blogbackend.controller;


import blog.blogbackend.dto.CommentDto;
import blog.blogbackend.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blogs")
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{blogId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("blogId") long blogId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto response = this.commentService.createComment(blogId, commentDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
