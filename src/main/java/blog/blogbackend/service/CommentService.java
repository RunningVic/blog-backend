package blog.blogbackend.service;

import blog.blogbackend.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(long blogId, CommentDto commentDto);
}
