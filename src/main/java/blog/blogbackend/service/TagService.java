package blog.blogbackend.service;

import blog.blogbackend.Repository.TagRepository;
import blog.blogbackend.entity.Tag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class TagService {
    @Resource
    TagRepository tagRepository;

    public Tag createTag(Tag tag) {
        return this.tagRepository.save(tag);
    }

    public Optional<Tag> getById(long id) {
        return this.tagRepository.findById(id);
    }
}
