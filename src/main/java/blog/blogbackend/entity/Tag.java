package blog.blogbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @NotBlank(message = "Category must not be empty.")
    private String category;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<Blog> blogs;
}
