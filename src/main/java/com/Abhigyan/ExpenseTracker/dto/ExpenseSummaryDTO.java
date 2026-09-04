package com.Abhigyan.Expensetracker.dto;

import java.util.Map;

// Response shape for GET /api/expenses/summary - total spend plus a
// category-name -> total-amount breakdown for the requested month.
public class ExpenseSummaryDTO {

    private Double totalAmount;
    private Map<String, Double> categoryBreakdown;

    public ExpenseSummaryDTO() {
    }

    public ExpenseSummaryDTO(Double totalAmount, Map<String, Double> categoryBreakdown) {
        this.totalAmount = totalAmount;
        this.categoryBreakdown = categoryBreakdown;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Map<String, Double> getCategoryBreakdown() {
        return categoryBreakdown;
    }

    public void setCategoryBreakdown(Map<String, Double> categoryBreakdown) {
        this.categoryBreakdown = categoryBreakdown;
    }
}
