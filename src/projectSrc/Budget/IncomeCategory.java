

package src.projectSrc.Budget;


public enum IncomeCategory {

    SALARY(1, "salary"),
    INVESTMENT(2, "investment"),
    SCHOLARSHIP(3,"scholarship"),
    OTHERINCOME(4,"other income");

    private int incomeNumber;
    private String incomeCategoryName;

    IncomeCategory(int a, String b){
        incomeNumber = a;
        incomeCategoryName = b;
    }

    @Override
    public String toString() {
        return " {" +
                "Income number = '" + incomeNumber + " category " + incomeCategoryName + '\'' +
                '}';
    }

    public void setIncomeNumber(int incomeNumber) {
        this.incomeNumber = incomeNumber;
    }

    public void setIncomeCategoryName(String incomeCategoryName) {
        this.incomeCategoryName = incomeCategoryName;
    }

    public int getIncomeNumber() {
        return incomeNumber;
    }

    public String getIncomeCategoryName() {
        return incomeCategoryName;
    }

    public static IncomeCategory incomeCategoryByNumber(int a){
        for(IncomeCategory iC : values()){
            if(iC.getIncomeNumber() == a) {
                return iC;
            }
        }
        return null;
    }
}

