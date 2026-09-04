package com.Abhigyan.Expensetracker.service;

import com.Abhigyan.Expensetracker.dto.ExpenseRequestDTO;
import com.Abhigyan.Expensetracker.dto.ExpenseResponseDTO;
import com.Abhigyan.Expensetracker.dto.ExpenseSummaryDTO;
import com.Abhigyan.Expensetracker.exception.ResourceNotFoundException;
import com.Abhigyan.Expensetracker.model.Category;
import com.Abhigyan.Expensetracker.model.Expense;
import com.Abhigyan.Expensetracker.repository.CategoryRepository;
import com.Abhigyan.Expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ExpenseResponseDTO createExpense(ExpenseRequestDTO dto) {
        // Business rule: the categoryId sent by the client must refer to
        // a category that actually exists - if not, fail loudly with a
        // clear 404 rather than letting a bad foreign key hit the DB.
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));

        Expense expense = new Expense(dto.getTitle(), dto.getAmount(), dto.getDate(),
                dto.getDescription(), category);

        Expense saved = expenseRepository.save(expense);
        return toResponseDTO(saved);
    }

    @Override
    public List<ExpenseResponseDTO> getAllExpenses(Integer month, Long categoryId) {
        List<Expense> expenses;

        if (month != null && categoryId != null) {
            LocalDate[] range = monthRange(month);
            expenses = expenseRepository.findByCategoryIdAndDateBetween(categoryId, range[0], range[1]);
        } else if (month != null) {
            LocalDate[] range = monthRange(month);
            expenses = expenseRepository.findByDateBetween(range[0], range[1]);
        } else if (categoryId != null) {
            expenses = expenseRepository.findByCategoryId(categoryId);
        } else {
            expenses = expenseRepository.findAll();
        }

        return expenses.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseResponseDTO getExpenseById(Long id) {
        Expense expense = findExpenseOrThrow(id);
        return toResponseDTO(expense);
    }

    @Override
    public ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO dto) {
        Expense expense = findExpenseOrThrow(id);

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));

        // Mutate the existing managed entity - JPA's "dirty checking"
        // means we don't even need to explicitly call save(); Hibernate
        // detects the field changes and flushes an UPDATE automatically
        // at the end of the transaction. We call save() anyway for
        // clarity and to return the updated entity directly.
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        expense.setDescription(dto.getDescription());
        expense.setCategory(category);

        Expense updated = expenseRepository.save(expense);
        return toResponseDTO(updated);
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = findExpenseOrThrow(id);
        expenseRepository.delete(expense);
    }

    @Override
    public ExpenseSummaryDTO getSummary(Integer month, Integer year) {
        // Default to the current month/year if the caller doesn't specify.
        LocalDate today = LocalDate.now();
        int targetYear = (year != null) ? year : today.getYear();
        int targetMonth = (month != null) ? month : today.getMonthValue();

        YearMonth ym = YearMonth.of(targetYear, targetMonth);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();

        List<Expense> expenses = expenseRepository.findByDateBetween(start, end);

        double total = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        // Group by category name and sum amounts per group - this is the
        // "category-wise breakdown" the summary endpoint promises.
        Map<String, Double> breakdown = expenses.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCategory().getName(),
                        Collectors.summingDouble(Expense::getAmount)
                ));

        return new ExpenseSummaryDTO(total, breakdown);
    }

    // --- Private helpers ---

    private Expense findExpenseOrThrow(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
    }

    // Converts a month number (1-12, current year assumed) into a
    // [start, end] LocalDate range for the findByDateBetween query.
    private LocalDate[] monthRange(int month) {
        YearMonth ym = YearMonth.of(LocalDate.now().getYear(), month);
        return new LocalDate[]{ym.atDay(1), ym.atEndOfMonth()};
    }

    private ExpenseResponseDTO toResponseDTO(Expense expense) {
        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getDate(),
                expense.getDescription(),
                expense.getCategory().getName(),
                expense.getCreatedAt()
        );
    }
}
