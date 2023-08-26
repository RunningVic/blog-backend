package blog.blogbackend.controller;


import blog.blogbackend.dto.CommentDto;
import blog.blogbackend.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{blogId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByBlogId(@PathVariable("blogId")long blogId) {
        List<CommentDto> response = this.commentService.getCommentsByBlogId(blogId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{blogId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("blogId") long blogId,
                                                     @PathVariable("commentId") long commentId) {
        CommentDto resposne = this.commentService.getCommentById(commentId, blogId);
        return new ResponseEntity<>(resposne, HttpStatus.OK);
    }

    @DeleteMapping("/{blogId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("blogId") long blogId,
                                                    @PathVariable("commentId") long commentId) {
        this.commentService.deleteCommentById(blogId, commentId);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }

    @PutMapping("/{blogId}/comments/{commentId}")
    public ResponseEntity<CommentDto> deleteCommentById(@PathVariable("blogId") long blogId,
                                                    @PathVariable("commentId") long commentId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto response = this.commentService.updateComment(blogId, commentId, commentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
