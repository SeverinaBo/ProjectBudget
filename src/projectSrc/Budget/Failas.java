package src.projectSrc.Budget;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Failas {

    public Failas(String BudgetFile){

    }
    public static void saveData(ArrayList<Record> records) {
        PrintWriter pw = null;
        try {
            FileWriter fileWriter = new FileWriter("src.projectSrc/BudgetFile.csv",false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            pw = new PrintWriter(bufferedWriter);
            for (Record record : records) {
                pw.println(record.toCsv());
            }
            pw.flush();
            System.out.println("Data is saved");
        } catch (IOException e) {
            System.out.println("Error! Can't save to file");
        } catch (NullPointerException npe){
            System.out.println("PrintWriter can't close");
        } finally {
            assert pw != null;
            pw.close();
        }
    }


    public static ArrayList<Record> printData() throws IOException{
        ArrayList<Record> records = new ArrayList<>();
        String line = "";
        String path = "src.projectSrc/BudgetFile.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                records.add(recordFromCsvData(line));
            }
        } catch (IOException e) {
            System.out.println("Error...can't read data from file.");
        }
        return records;
    }


    private static Record recordFromCsvData(String csv) {
        String[] data = csv.split(",");
        Record record = null;
        if (IncomeRecord.recordType.equals(data[0])) {
            IncomeRecord incomeRecord = new IncomeRecord();
            incomeRecord.setId(Long.parseLong(data[1]));
            incomeRecord.setAmount(Double.parseDouble(data[2]));
            incomeRecord.setDate(LocalDate.parse(data[3]));
            incomeRecord.setInfo(data[4]);
            incomeRecord.setCategory(IncomeCategory.incomeCategoryByNumber(Integer.parseInt(data[5])));
            record = incomeRecord;
        } else if (ExpenseRecord.recordType.equals(data[0])) {
            ExpenseRecord expenseRecord = new ExpenseRecord();
            expenseRecord.setId(Long.parseLong(data[1]));
            expenseRecord.setAmount(Double.parseDouble(data[2]));
            expenseRecord.setDate(LocalDate.parse(data[3]));
            expenseRecord.setInfo(data[4]);
            expenseRecord.setCategory(ExpensesCategory.expensesCategoryByNumber(Integer.parseInt(data[5])));
            record = expenseRecord;
        }
        return record;
    }
}

