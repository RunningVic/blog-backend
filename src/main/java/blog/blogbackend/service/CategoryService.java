package blog.blogbackend.service;

import blog.blogbackend.Repository.CategoryRepository;
import blog.blogbackend.entity.Category;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CategoryService {
    @Resource
    CategoryRepository tagRepository;

    public Category createTag(Category tag) {
        return this.tagRepository.save(tag);
    }

    public Optional<Category> getById(long id) {
        return this.tagRepository.findById(id);
    }
}
