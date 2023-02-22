package src.projectSrc.Budget;


public enum ExpensesCategory {

    FOOD (1,"food"),
    HOUSE(2,"house"),
    TAXES(3,"taxes"),
    TRANSPORTATION(4,"transportation"),
    OTHEREXPENSES(5,"other expenses");

    private int expenseNumber;
    private String expensePurpose;

    public void setExpenseNumber(int expenseNumber) {
        this.expenseNumber = expenseNumber;
    }

    public void setExpensePurpose(String expensePurpose) {
        this.expensePurpose = expensePurpose;
    }

    @Override
    public String toString() {
        return " {" +
                "Expenses number = " + expenseNumber +
                " purpose - " + expensePurpose + '\'' +
                "}";
    }

    ExpensesCategory(int a, String b){
        expenseNumber = a;
        expensePurpose = b;
    }

    public static ExpensesCategory expensesCategoryByNumber(int i){
        for(ExpensesCategory eC : values()){
            if(eC.getExpenseNumber() == i){
                return eC;
            }
        }
        return expensesCategoryByNumber(i);
    }

    public int getExpenseNumber() {
        return expenseNumber;
    }

    public String getExpensePurpose() {
        return expensePurpose;
    }
}
