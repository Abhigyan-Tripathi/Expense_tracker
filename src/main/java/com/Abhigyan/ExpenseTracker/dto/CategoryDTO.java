package com.Abhigyan.Expensetracker.dto;

import jakarta.validation.constraints.NotBlank;

// Used for BOTH request (creating a category) and response - it's simple
// enough that one shape covers both. Notice it has no reference to
// Expense at all, unlike the Category entity - keeps the API response
// flat and avoids ever serializing a list of expenses inside a category
// response by accident.
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Category name must not be blank")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
