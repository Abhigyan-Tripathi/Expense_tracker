package com.Abhigyan.Expensetracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate date;

    // No "nullable = false" here -> description is optional.
    private String description;

    // This is the OWNING side of the relationship: @JoinColumn tells
    // Hibernate to create a "category_id" foreign-key column on the
    // "expense" table that points to category.id. Many expenses can point
    // to the same category (hence @ManyToOne).
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private LocalDateTime createdAt;

    public Expense() {
    }

    public Expense(String title, Double amount, LocalDate date, String description, Category category) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
    }

    // @PrePersist runs automatically right before this entity is first
    // saved to the database - a clean way to auto-stamp createdAt without
    // relying on the caller to set it.
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Getters and setters ---

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
