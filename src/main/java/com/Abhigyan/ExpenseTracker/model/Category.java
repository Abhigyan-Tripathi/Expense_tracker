package com.Abhigyan.Expensetracker.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Entity tells Hibernate this class maps to a database table.
// By default the table is named "category" (lowercase class name).
@Entity
public class Category {

    @Id
    // IDENTITY strategy = let the database auto-increment the primary key
    // (this is how H2/Postgres/MySQL all handle auto-incrementing IDs).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // unique = true adds a UNIQUE constraint on this column so two
    // categories can't share the same name. nullable = false means the
    // database will reject inserts without a name.
    @Column(unique = true, nullable = false)
    private String name;

    // The "inverse" side of the Expense <-> Category relationship.
    // mappedBy = "category" points to the "category" field on Expense.java
    // - this does NOT create a new column, it just lets us navigate
    // category.getExpenses() in Java. The actual foreign key lives on the
    // Expense table (see @JoinColumn there).
    @OneToMany(mappedBy = "category")
    private List<Expense> expenses = new ArrayList<>();

    // JPA requires a no-argument constructor so Hibernate can instantiate
    // entities via reflection when reading rows from the database.
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    // --- Getters and setters ---
    // Explicit (no Lombok) so the mechanics stay visible while learning.

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

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
