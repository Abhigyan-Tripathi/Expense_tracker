package com.Abhigyan.Expensetracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

// What we send BACK to the client. Notice categoryName is a flat String,
// not the full Category entity - the client doesn't need Category's
// internal id/expenses list, just the name to display. This is the whole
// point of DTOs: shape the response exactly to what's useful, independent
// of how the data is actually stored.
public class ExpenseResponseDTO {

    private Long id;
    private String title;
    private Double amount;
    private LocalDate date;
    private String description;
    private String categoryName;
    private LocalDateTime createdAt;

    public ExpenseResponseDTO() {
    }

    public ExpenseResponseDTO(Long id, String title, Double amount, LocalDate date,
                               String description, String categoryName, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryName = categoryName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
