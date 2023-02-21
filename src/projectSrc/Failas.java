package src.projectSrc;



import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;


public class Failas {

    public Failas(String files){

    }
    public static void saveData(ArrayList<Record> records) {
        PrintWriter printWriter = null;
        try {
            FileWriter fileWriter = new FileWriter("src/failas.csv",false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            for (Record record : records) {
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


    public static ArrayList<Record> printData() throws IOException{
        ArrayList<Record> records = new ArrayList<Record>();
        String line = "";
        String path = "src/failas.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                records.add(recordFromCsvData(line));
//                irasai.add(irasasIsCSV(linija));
            }
        } catch (IOException e) {
            System.out.println("Error...can't read data from file.");
        }
        return records;
    }


    private static Record recordFromCsvData(String csv) {
        String[] data = csv.split(",");
        Record record = new Record();
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

