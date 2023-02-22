package src.projectSrc.Budget;

import java.util.ArrayList;

public class Budget {

    public ArrayList<Record> records = new ArrayList<>();
    public void addRecord(Record record) {
        records.add(record);
    }

   public  ArrayList<Record> receiveAllRecords() {
        return records;
    }

    public Record printRecordById(long id) {
        for (Record record : records) {
            if (record.getId() == id) {
                return record;
            }
        }
        return null;
    }

    public ArrayList<Record> receiveAllIncomeRecords() {
        ArrayList<Record> incomeRecords = new ArrayList<Record>();
        for (Record record : records) {
            if (record instanceof IncomeRecord) {
                incomeRecords.add(record);
            }
        }
        return incomeRecords;
    }

    public  ArrayList<Record> receiveAllExpenseRecords() {
        ArrayList<Record> expensesRecords = new ArrayList<Record>();
        for (Record record : records) {
            if (record instanceof ExpenseRecord) {
                expensesRecords.add(record);
            }
        }
        return expensesRecords;
    }
    public float balance() {
        float balance = 0;
            for(Record record : records){
                if(record instanceof IncomeRecord){
                    balance += record.getAmount();
                } else if (record instanceof ExpenseRecord) {
                    balance -= record.getAmount();
                }
            }
            return balance;
        }

    public boolean deleteRecord(long id) {
        Record emptyRecord = new Record();
        emptyRecord.setId(id);
        return records.remove(emptyRecord);
    }

    public boolean renewRecord(Record record) {
        deleteRecord(record.getId());
        return records.add(record);
    }
}



