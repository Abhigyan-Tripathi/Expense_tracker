package com.Abhigyan.Expensetracker.controller;

import com.Abhigyan.Expensetracker.dto.ExpenseRequestDTO;
import com.Abhigyan.Expensetracker.dto.ExpenseResponseDTO;
import com.Abhigyan.Expensetracker.dto.ExpenseSummaryDTO;
import com.Abhigyan.Expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> createExpense(@Valid @RequestBody ExpenseRequestDTO dto) {
        ExpenseResponseDTO created = expenseService.createExpense(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    // @RequestParam(required = false) means these are OPTIONAL query
    // params: GET /api/expenses?month=8&categoryId=2
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses(
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(expenseService.getAllExpenses(month, categoryId));
    }

    @GetMapping("/{id}")
    // @PathVariable binds the {id} segment of the URL to this parameter.
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(
            @PathVariable Long id, @Valid @RequestBody ExpenseRequestDTO dto) {
        return ResponseEntity.ok(expenseService.updateExpense(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        // 204 No Content - standard convention for a successful DELETE
        // with no body to return.
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public ResponseEntity<ExpenseSummaryDTO> getSummary(
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(expenseService.getSummary(month, year));
    }
}
