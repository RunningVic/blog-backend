package blog.blogbackend.service;

import blog.blogbackend.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long blogId, CommentDto commentDto);

    List<CommentDto> getCommentsByBlogId(long blogId);

    CommentDto getCommentById(long commentId, long blogId);

    void deleteCommentById(long blogId, long commentId);

    CommentDto updateComment(long blogId, long commentId, CommentDto commentDto);
}
