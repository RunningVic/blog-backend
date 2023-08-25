package blog.blogbackend.entity;

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
@Table(name = "blogs", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    // @NotBlank(message = "The content must not be empty.")
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @Column(name = "last_modified_at", nullable = false)
    private Date lastModifiedAt;
}
