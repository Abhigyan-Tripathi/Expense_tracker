package com.Abhigyan.Expensetracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// What the CLIENT sends us. Deliberately does NOT include "id" or
// "createdAt" - the client shouldn't be setting those, the server decides
// them. Also takes "categoryId" (just a number) rather than a full
// Category object - the client only needs to reference which category,
// not construct one.
public class ExpenseRequestDTO {

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be zero or positive")
    private Double amount;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private String description; // optional, no validation annotation needed

    @NotNull(message = "categoryId is required")
    private Long categoryId;

    public ExpenseRequestDTO() {
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
