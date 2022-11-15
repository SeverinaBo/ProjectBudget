package src.projectSrc;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Record {
    static ArrayList<Record> recordIncomeArr = new ArrayList<>();
    static ArrayList<Record> recordExpensesArr = new ArrayList<>();

    private static long counter = 0;

    public Record() {

    }

    public Record(ArrayList<Record> recordIncomeArr) {
    }

    public void Record() {
        this.id = counter++;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public Record(long id,
                  double incomeAmount,
                  String incomeInfo,
                  IncomeCategory incomeCategoryEnum,
                  IncomeCategory incomeCategory,
                  LocalDate localDate,
                  double expensesAmount,
                  String expensesInfo,
                  ExpensesCategory expenseCategoryEnum,
                  String expensesPaymentMethod,
                  ExpensesCategory expensesCategory) {
        this.id = id;
        this.incomeAmount = incomeAmount;
        this.incomeInfo = incomeInfo;
        this.incomeCategoryEnum = incomeCategoryEnum;
        this.incomeCategory = incomeCategory;
        this.localDate = localDate;
        this.expensesAmount = expensesAmount;
        this.expensesInfo = expensesInfo;
        this.expenseCategoryEnum = expenseCategoryEnum;
        this.expensesPaymentMethod = expensesPaymentMethod;
        this.expensesCategory = expensesCategory;
    }

    private double incomeAmount;

    private String incomeInfo;
    IncomeCategory incomeCategoryEnum;
    private IncomeCategory incomeCategory;


    public LocalDate getLocalDate() {
        return LocalDate.now();
    }

    private LocalDate localDate;


    private double expensesAmount;
    protected String expensesInfo;
    ExpensesCategory expenseCategoryEnum;
    private String expensesPaymentMethod;
    private ExpensesCategory expensesCategory;

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public void setIncomeInfo(String incomeInfo) {
        this.incomeInfo = incomeInfo;
    }

    public void setIncomeCategoryEnum(IncomeCategory incomeCategoryEnum) {
        this.incomeCategoryEnum = incomeCategoryEnum;
    }

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(IncomeCategory incomeCategory) {
        this.incomeCategory = incomeCategory;
    }


    public void setLocalDate(LocalDate localDate) {
        this.localDate = LocalDate.now();
    }


    public void setExpensesAmount(double expensesAmount) {
        this.expensesAmount = expensesAmount;
    }


    public void setExpensesInfo(String expensesInfo) {
        this.expensesInfo = expensesInfo;
    }

    public void setExpenseCategoryEnum(ExpensesCategory expenseCategoryEnum) {
        this.expenseCategoryEnum = expenseCategoryEnum;
    }

    public void setExpensesPaymentMethod(String textInput) {
        this.expensesPaymentMethod = expensesPaymentMethod;
    }

    public ExpensesCategory getExpensesCategory() {
        return expensesCategory;
    }

    public void setExpensesCategory(ExpensesCategory expensesCategory) {
        this.expensesCategory = expensesCategory;
    }


    public String toCsv() {
        return String.format ("%d,%s,%s,%s,%s,%s", RECORD_INCOME , id , incomeAmount , incomeInfo , incomeCategory.getIncomeNumber() ,
                "%d,%s,%s,%s,%s,%s" , RECORD_EXPENSES , id , expensesAmount , expensesInfo , expensesCategory.getExpenseNumber());
    }

    public static final String RECORD_EXPENSES = "Expenses record: ";

    public static final String RECORD_INCOME = "Income record: ";

    @Override
    public String toString() {
        return String.format (RECORD_INCOME, ", category: %s, %s" , incomeCategory , super.toString() ,
                RECORD_EXPENSES , ", category: %s, %s" , expensesCategory, super.toString());
    }

    public void localDate() {
    }

    public boolean equals(Object o) {
        return o instanceof Record && id == ((Record) o).getId();
    }


    public double getExpensesAmount() {
        return expensesAmount;
    }



    public double getIncomeAmount() {

        return incomeAmount;
    }
}
