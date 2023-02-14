package src.projectSrc;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Record {
   public static ArrayList<Record> recordIncomeArr = new ArrayList<>();
   public static ArrayList<Record> recordExpensesArr = new ArrayList<>();

    private static long recordCounter = 0;

    public Record(ArrayList<Record> recordExpensesArr) {
    }
    public Record() {
    }

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


    public static ArrayList<Record> getRecordIncomeArr() {
        return recordIncomeArr;
    }

    public static void setRecordIncomeArr(ArrayList<Record> recordIncomeArr) {
        Record.recordIncomeArr = recordIncomeArr;
    }

    public static ArrayList<Record> getRecordExpensesArr() {
        return recordExpensesArr;
    }

    public static void setRecordExpensesArr(ArrayList<Record> recordExpensesArr) {
        Record.recordExpensesArr = recordExpensesArr;
    }

    public static long getCounter() {
        return recordCounter;
    }

    public static void setRecordCounter(long recordCounter) {
        Record.recordCounter = recordCounter;
    }

    public String getIncomeInfo() {
        return incomeInfo;
    }

    public IncomeCategory getIncomeCategoryEnum() {
        return incomeCategoryEnum;
    }

    public String getExpensesInfo() {
        return expensesInfo;
    }

    public ExpensesCategory getExpenseCategoryEnum() {
        return expenseCategoryEnum;
    }

    public String getExpensesPaymentMethod() {
        return expensesPaymentMethod;
    }

    public void recordCounter() {
        this.id = recordCounter++;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;


    private double incomeAmount;

    private String incomeInfo;
    IncomeCategory incomeCategoryEnum;
    private IncomeCategory incomeCategory;


    public LocalDate getLocalDate() {
        return localDate;
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
        this.localDate = localDate;
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
        this.expensesPaymentMethod = textInput;
    }

    public ExpensesCategory getExpensesCategory() {
        return expensesCategory;
    }

    public void setExpensesCategory(ExpensesCategory expensesCategory) {
        this.expensesCategory = expensesCategory;
    }


    public String toCsv() {
        if (incomeAmount > 0) {
            return String.format("%s%d,%.2f,%s,%d\n", RECORD_INCOME, id, incomeAmount, incomeInfo, incomeCategory.getIncomeNumber());
        } else {
            return String.format("%s%d,%.2f,%s,%d\n", RECORD_EXPENSES, id, expensesAmount, expensesInfo, expensesCategory.getExpenseNumber());
        }
    }


    public static final String RECORD_EXPENSES = "Expenses record: ";

    public static final String RECORD_INCOME = "Income record: ";

    @Override
    public String toString() {
        if (incomeAmount > 0) {
            return String.format("%s [id = %d, amount = %.2f, info = %s, category = %s]", RECORD_INCOME, id, incomeAmount, incomeInfo, incomeCategory);
        } else {
            return String.format("%s [id = %d, amount = %.2f, info = %s, category = %s]", RECORD_EXPENSES, id, expensesAmount, expensesInfo, expensesCategory);
        }
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
