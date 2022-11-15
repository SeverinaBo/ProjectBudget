package src.projectSrc;



import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;


public class Failas {

    public Failas(String file){

    }
    public static void saveData(ArrayList<Record> incomeExpenseArr) {
        PrintWriter printWriter = null;
        try {
            FileWriter fileWriter = new FileWriter("src/failas.csv",false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            for (Record record : incomeExpenseArr) {
                printWriter.println(record.toCsv());
            }
            printWriter.flush();
            System.out.println("Data is saved");
        } catch (IOException e) {
            printWriter.println("Error!Can't save to file");
        } catch (NullPointerException npe){
            out.println("PrintWriter can't close");;
        } finally {
            assert printWriter != null;
            printWriter.close();
        }
    }


    public static List<Record> printData() {
        ArrayList<Record> records = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try{
            File fileIs = new File("src/failas.csv");
            FileInputStream fis = new FileInputStream(fileIs);
            bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                records.add(recordFromCsvData(line));
            }
        }
        catch (IOException e) {
            out.println("Klaida! Napavyko nuskaityti duomenu is failo");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                out.println("Klaida! Napavyko uzdaryti buffered readerio");
            }
        }
        return records;
    }



    private static Record recordFromCsvData(String csv) {
        String[] data = csv.split(",");
        Record record = new Record();
        if (Record.RECORD_INCOME.equals(data[0])) {
            Record incomeRecord = new Record();
            incomeRecord.setId(Long.parseLong(data[1]));
            incomeRecord.setIncomeAmount(Double.parseDouble(data[2]));
            incomeRecord.setLocalDate(LocalDate.parse(data[3]));
            incomeRecord.setIncomeInfo(data[4]);
            incomeRecord.setIncomeCategory(IncomeCategory.incomeCategoryByNumber(Integer.parseInt(data[5])));
            record = incomeRecord;

        } else if (Record.RECORD_EXPENSES.equals(data[0])) {
            Record expenseRecord = new Record();
            expenseRecord.setId(Long.parseLong(data[1]));
            expenseRecord.setExpensesAmount(Double.parseDouble(data[2]));
            expenseRecord.setLocalDate(LocalDate.parse(data[3]));
            expenseRecord.setExpensesInfo(data[4]);
            expenseRecord.setExpensesCategory(ExpensesCategory.expensesCategoryByNumber(Integer.parseInt(data[5])));
            expenseRecord.setExpensesPaymentMethod(data[6]);
            record = expenseRecord;
        }
        return record;
    }
}

