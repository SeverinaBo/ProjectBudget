package src.projectSrc;






import java.time.LocalDate;
import java.util.*;



public class Budget extends Record{





    public  Budget(long id, double incomeAmount, String incomeInfo, IncomeCategory incomeCategoryEnum, IncomeCategory incomeCategory, LocalDate localDate, double expensesAmount, String expensesInfo, ExpensesCategory expenseCategoryEnum, String expensesPaymentMethod, ExpensesCategory expensesCategory) {
        super(id, incomeAmount, incomeInfo, incomeCategoryEnum, incomeCategory, localDate, expensesAmount, expensesInfo, expenseCategoryEnum, expensesPaymentMethod, expensesCategory);
    }

    public Budget() {

    }
    public   List<Record> receiveAllRecords() {
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
        for (Record record : incomeRecordArr) {
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





    public  double calculateBalance() {
        double balance = 0;
        for(Record record : receiveAllRecords()) {
            if (record != null) {
                balance += record.getIncomeAmount();
                balance -= record.getExpensesAmount();
            }
        }
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



