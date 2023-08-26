package blog.blogbackend.service.implementation;

import blog.blogbackend.Repository.BlogRepository;
import blog.blogbackend.Repository.CommentRepository;
import blog.blogbackend.dto.CommentDto;
import blog.blogbackend.entity.Blog;
import blog.blogbackend.entity.Comment;
import blog.blogbackend.exception.BlogApiException;
import blog.blogbackend.exception.ResourceNotFoundException;
import blog.blogbackend.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CommentDto> getCommentsByBlogId(long blogId) {
        List<Comment> comments = this.commentRepository.findAllByBlogId(blogId);
        List<CommentDto> response = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return response;
    }

    @Override
    public CommentDto getCommentById(long commentId, long blogId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));
        Blog blog = this.blogRepository.findById(blogId).orElseThrow(
                () -> new ResourceNotFoundException("Blog", "id", blogId));
        if (comment.getBlog().getId() != blog.getId()) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to blog");
        }
        return mapToDto(comment);
    }

    @Override
    public void deleteCommentById(long blogId, long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));
        Blog blog = this.blogRepository.findById(blogId).orElseThrow(
                () -> new ResourceNotFoundException("Blog", "id", blogId));
        if (comment.getBlog().getId() != blog.getId()) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to blog");
        }
        this.commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDto updateComment(long blogId, long commentId, CommentDto commentDto) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));
        Blog blog = this.blogRepository.findById(blogId).orElseThrow(
                () -> new ResourceNotFoundException("Blog", "id", blogId));
        if (comment.getBlog().getId() != blog.getId()) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to blog");
        }
        comment.setBody(commentDto.getBody());
        Comment updatedComment = this.commentRepository.save(comment);
        return mapToDto(updatedComment);
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
