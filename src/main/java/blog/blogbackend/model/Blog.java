package blog.blogbackend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The title must not be empty.")
    private String title;
    // @NotBlank(message = "The content must not be empty.")
    private Date createdAt;
    private Date lastModifiedAt;
}
