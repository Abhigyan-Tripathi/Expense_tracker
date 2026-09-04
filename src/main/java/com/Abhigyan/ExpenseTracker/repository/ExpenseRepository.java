package com.Abhigyan.Expensetracker.repository;

import com.Abhigyan.Expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Generates: SELECT * FROM expense WHERE category_id = ?
    List<Expense> findByCategoryId(Long categoryId);

    // Generates: SELECT * FROM expense WHERE date BETWEEN ? AND ?
    // We use this for both the month filter and the summary endpoint -
    // the service layer computes the start/end LocalDate of the month
    // and passes them in here.
    List<Expense> findByDateBetween(LocalDate start, LocalDate end);

    // Combines both filters: category AND date range
    List<Expense> findByCategoryIdAndDateBetween(Long categoryId, LocalDate start, LocalDate end);
}
