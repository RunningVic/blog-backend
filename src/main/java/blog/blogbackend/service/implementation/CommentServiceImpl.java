package blog.blogbackend.service.implementation;

import blog.blogbackend.Repository.BlogRepository;
import blog.blogbackend.Repository.CommentRepository;
import blog.blogbackend.dto.CommentDto;
import blog.blogbackend.entity.Blog;
import blog.blogbackend.entity.Comment;
import blog.blogbackend.exception.ResourceNotFoundException;
import blog.blogbackend.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private BlogRepository blogRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }
    @Override
    public CommentDto createComment(long blogId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Blog blog = this.blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog", "id", blogId));
        comment.setBlog(blog);
        Date now = new Date();
        comment.setCreatedAt(now);
        Comment savedComment= commentRepository.save(comment);
        return mapToDto(savedComment);
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setBody(comment.getBody());
        commentDto.setId(comment.getId());
        commentDto.setLikes(comment.getLikes());
        commentDto.setUsername(comment.getUsername());
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setUsername(commentDto.getUsername());
        comment.setLikes(commentDto.getLikes());
        comment.setCreatedAt(commentDto.getCreatedAt());
        return comment;
    }
}
