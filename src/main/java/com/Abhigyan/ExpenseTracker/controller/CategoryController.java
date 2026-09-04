package com.Abhigyan.Expensetracker.controller;

import com.Abhigyan.Expensetracker.dto.CategoryDTO;
import com.Abhigyan.Expensetracker.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController = @Controller + @ResponseBody combined - every method's
// return value is automatically serialized to JSON (via Jackson) instead
// of being treated as a view name.
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    // @Valid triggers Bean Validation on the incoming CategoryDTO
    // (checks @NotBlank etc.) BEFORE this method body even runs. If
    // validation fails, Spring throws MethodArgumentNotValidException,
    // which our GlobalExceptionHandler catches and turns into a 400.
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO dto) {
        CategoryDTO created = categoryService.createCategory(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
