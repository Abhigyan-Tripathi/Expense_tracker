package com.Abhigyan.Expensetracker.service;

import com.Abhigyan.Expensetracker.dto.CategoryDTO;
import java.util.List;

// Coding to an interface (rather than calling CategoryServiceImpl
// directly) is a common Spring pattern - it makes swapping
// implementations or mocking this in tests easier later.
public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO dto);
    List<CategoryDTO> getAllCategories();
}
