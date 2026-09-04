package com.Abhigyan.Expensetracker.service;

import com.Abhigyan.Expensetracker.dto.ExpenseRequestDTO;
import com.Abhigyan.Expensetracker.dto.ExpenseResponseDTO;
import com.Abhigyan.Expensetracker.dto.ExpenseSummaryDTO;
import java.util.List;

public interface ExpenseService {
    ExpenseResponseDTO createExpense(ExpenseRequestDTO dto);
    List<ExpenseResponseDTO> getAllExpenses(Integer month, Long categoryId);
    ExpenseResponseDTO getExpenseById(Long id);
    ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO dto);
    void deleteExpense(Long id);
    ExpenseSummaryDTO getSummary(Integer month, Integer year);
}
