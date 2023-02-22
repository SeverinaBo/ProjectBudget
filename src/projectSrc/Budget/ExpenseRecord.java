package src.projectSrc.Budget;

import java.time.LocalDate;

public class ExpenseRecord extends Record {
    public final static String recordType = "EXPENSE RECORD";

    private ExpensesCategory category;
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ExpenseRecord() {
        super();
    }

    public ExpenseRecord(double amount, LocalDate date, String info, ExpensesCategory category) {
        super(amount, date, info);
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, Caregory: %s",recordType, super.toString(), category );
    }

    public ExpensesCategory getCategory() {
        return category;
    }

    public void setCategory(ExpensesCategory category) {
        this.category = category;
    }

    @Override
    public String toCsv() {
        return String.format("%s, %s, %s, %s", recordType, super.toCsv(), category.getExpenseNumber(), category.getExpensePurpose());
    }
}