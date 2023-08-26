package blog.blogbackend.dto;

import lombok.*;

import java.util.Date;

@Data
public class CommentDto {
    private long id;
    private String username;
    private String body;
    private Date createdAt;
    private long likes;
}
