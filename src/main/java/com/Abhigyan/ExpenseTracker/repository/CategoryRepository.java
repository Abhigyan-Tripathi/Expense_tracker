package com.Abhigyan.Expensetracker.repository;

import com.Abhigyan.Expensetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Extending JpaRepository<Category, Long> gives us save(), findAll(),
// findById(), deleteById(), count(), etc. for FREE - no implementation
// needed, Spring Data JPA generates it at runtime.
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Spring parses this method name and auto-generates the SQL:
    // "SELECT * FROM category WHERE name = ?"
    // This is "derived query" magic - the method name IS the query.
    Optional<Category> findByName(String name);
}
