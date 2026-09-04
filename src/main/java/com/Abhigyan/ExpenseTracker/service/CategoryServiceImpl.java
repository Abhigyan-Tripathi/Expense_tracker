package com.Abhigyan.Expensetracker.service;

import com.Abhigyan.Expensetracker.dto.CategoryDTO;
import com.Abhigyan.Expensetracker.model.Category;
import com.Abhigyan.Expensetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

// @Service marks this as a Spring-managed bean in the business-logic
// layer. Spring finds it via component scanning and injects it wherever
// CategoryService is @Autowired (e.g. into CategoryController).
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    // Constructor injection (preferred over field @Autowired) - makes
    // dependencies explicit and the class easy to unit test by just
    // passing in a mock repository.
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = new Category(dto.getName());
        Category saved = categoryRepository.save(category);
        return toDTO(saved);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Entity -> DTO mapping lives here in the service, never in the
    // controller. Kept as a small private helper for reuse.
    private CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
