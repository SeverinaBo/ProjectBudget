package src.projectSrc;
import java.time.LocalDate;
import java.util.*;



public class Budget extends Record{

    public  Budget(long id, double incomeAmount, String incomeInfo, IncomeCategory incomeCategoryEnum, IncomeCategory incomeCategory, LocalDate localDate, double expensesAmount, String expensesInfo, ExpensesCategory expenseCategoryEnum, String expensesPaymentMethod, ExpensesCategory expensesCategory) {
        super(id, incomeAmount, incomeInfo, incomeCategoryEnum, incomeCategory, localDate, expensesAmount, expensesInfo, expenseCategoryEnum, expensesPaymentMethod, expensesCategory);
    }

    public Budget() {

    }
    public  List<Record> receiveAllRecords() {
        List<Record> incomeExpenseArr = new ArrayList<>();
        incomeExpenseArr.addAll(recordIncomeArr);
        incomeExpenseArr.addAll(recordExpensesArr);
        return incomeExpenseArr;
    }

    public static void addRecord(Record record) {
        recordIncomeArr.add(record);
        recordExpensesArr.add(record);

    }

    public static Record printRecordById(long id) {
        for (Record record : recordIncomeArr) {
            if (record.getId() == id) {
                return record;
            }
        }
        return null;
    }
    public  ArrayList<Record> receiveAllIncomeRecords() {
        ArrayList<Record> incomeRecordArr = new ArrayList<>();
        for (Record record : recordIncomeArr) {
            if (record != null) {
                incomeRecordArr.add(record);
            }
        }
        return incomeRecordArr;
    }

    public  ArrayList<Record> receiveAllExpenseRecords() {
        ArrayList<Record> expenseRecordArr = new ArrayList<>();
        for (Record record : recordExpensesArr) {
            if (record != null) {
                expenseRecordArr.add(record);
            }
        }
        return expenseRecordArr;
    }


    public double calculateBalance() {
        double incomeTotal = 0;
        double expensesTotal = 0;

        // Calculate the total income
        for (Record record : recordIncomeArr) {
            incomeTotal += record.getIncomeAmount();
        }

        // Calculate the total expenses
        for (Record record : recordExpensesArr) {
            expensesTotal += record.getExpensesAmount();
        }

        // Calculate the balance
        double balance = incomeTotal - expensesTotal;
        return balance;
    }



    public void deleteRecord(long id) {
        Record emptyRecord = new Record();
        emptyRecord.setId(id);
        receiveAllRecords().remove(emptyRecord);
    }

    public void renewRecord(Record record) {
        deleteRecord(record.getId());
        receiveAllRecords().add(record);
    }

}



