package blog.blogbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;
    @NotBlank(message = "The title must not be empty.")
    private String title;
    // @NotBlank(message = "The content must not be empty.")
    private Date createdAt;
    private Date lastModifiedAt;

    @ManyToMany()
    @JoinTable(
            name = "blog_tag",
            joinColumns = { @JoinColumn(name = "blogId") },
            inverseJoinColumns = { @JoinColumn(name = "tagId") }
    )
    @NotEmpty(message = "Tags can not be empty.")
    private Set<Tag> tags;
}
