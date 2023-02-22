package src.projectSrc.Budget;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Application {
    public static ArrayList<Record> records = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    static Budget budget = new Budget();
    static Record record = new Record();

    public static void main(String[] args) throws IOException {

        boolean proceedApp = true;
        while (proceedApp) {
            int input = mainMenu(scanner);
            switch (input) {
                case 1 -> recordInputs(scanner);
                case 2 -> receiveRecords(scanner);
                case 3 -> editRecord(scanner, record, budget);
                case 4 -> printBalance(budget);
                case 5 -> budget.receiveAllRecords().addAll(Failas.printData());
                case 6 -> Failas.saveData(budget.receiveAllRecords());
                case 7 -> deleteRecord(scanner, record, budget);
                case 8 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                }
                default -> {
                    System.out.println("Invalid choice.");
                    mainMenu(scanner);
                }
            }
        }
        while (true) ;
    }

        private static int mainMenu (Scanner scanner){
            System.out.println
                    ("""
                            [1] - log new record
                            [2] - print all records
                            [3] - edit records
                            [4] - balance
                            [5] - count balance
                            [6] - save to file
                            [7] - delete record(will need ID)
                            [8] - close the program\s""");
            return numberChoice(scanner, 1, 2, 3, 4, 5, 6, 7, 8);
        }

        private static Record newIncomeRecord (Scanner scanner){
            IncomeRecord incomeRecord = new IncomeRecord();

            System.out.println("Income amount: ");
            try {
                incomeRecord.setAmount(scanner.nextDouble());
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number ");
            }

            System.out.println("Income category: ");
            for (IncomeCategory iC : IncomeCategory.values()) {
                System.out.println(iC);
            }

            int choice = scanner.nextInt();
            incomeRecord.setCategory(IncomeCategory.incomeCategoryByNumber(choice));

            System.out.println("Income information: ");
            String incomeInfo = scanner.next();
            incomeRecord.setInfo(incomeInfo);
            incomeRecord.setDate(LocalDate.now());

            System.out.println(incomeRecord);
            return incomeRecord;
        }

        private static Record newExpensesRecord (Scanner scanner){
            ExpenseRecord expensesRecord = new ExpenseRecord();

            System.out.println("Expense amount: ");
            try {
                expensesRecord.setAmount(scanner.nextDouble());
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number ");
            }

            System.out.println("Expense category: ");
            for (ExpensesCategory eC : ExpensesCategory.values()) {
                System.out.println(eC);
            }

            int choice2 = scanner.nextInt();
            expensesRecord.setCategory(ExpensesCategory.expensesCategoryByNumber(choice2));

            System.out.println("Expense information:");
            expensesRecord.setInfo(scanner.next());
            expensesRecord.setDate(LocalDate.now());

            System.out.println(expensesRecord);
            return expensesRecord;
        }



        private static void deleteRecord (Scanner scanner, Record record, Budget budget){
            System.out.println("Input ID:");
            long id = numberChoice(scanner);
            if (Objects.equals(id, record.getId())) {
                System.out.println("There is no record with this ID, try again a different ID");
            } else {
                budget.deleteRecord(id);
            }
        }


        private static void editRecord (Scanner scanner, Record record, Budget budget){
            System.out.println("Input Id");
            long id = numberChoice(scanner);
            record = budget.printRecordById(id);
            if (record != null) {
                System.out.println("Amount: " + record.getAmount() + ". Edit: [Y] - yes, [N] - no");
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("y")) {
                    System.out.println("Input new amount");
                    record.setAmount(scanner.nextDouble());
                }
                if (record instanceof IncomeRecord) {
                    System.out.println("Category: " + ((IncomeRecord) record).getCategory().getIncomeCategoryName() + ". Edit: [Y] - yes, [N] - no");
                    String choice1 = scanner.next();
                    if (choice1.equalsIgnoreCase("y")) {
                        System.out.println("Choose new category: ");
                        for (IncomeCategory iC : IncomeCategory.values()) {
                            System.out.println(iC);
                        }
                        int categ = scanner.nextInt();
                        ((IncomeRecord) record).setCategory(IncomeCategory.incomeCategoryByNumber(categ));
                    }
                } else if (record instanceof ExpenseRecord) {
                    System.out.println("Category: " + ((ExpenseRecord) record).getCategory().getExpensePurpose() + ". Edit: [Y] - yes, [N] - no");
                    String choice2 = scanner.next();
                    if (choice2.equalsIgnoreCase("y")) {
                        System.out.println("Choose new category: ");
                        for (ExpensesCategory eC : ExpensesCategory.values()) {
                            System.out.println(eC);
                        }
                        int categ = scanner.nextInt();
                        ((ExpenseRecord) record).setCategory(ExpensesCategory.expensesCategoryByNumber(categ));
                    }
                }
                System.out.println("Extra info: " + record.getInfo() + ". Edit: [Y] - yes, [N] - no");
                String info = scanner.next();
                if (info.equalsIgnoreCase("Y")) {
                    System.out.println("Input new information: ");
                    String updatedInfo = scanner.next();
                    record.setInfo(updatedInfo);
                    budget.renewRecord(record);
                } else {
                    System.out.println("continue...");
                }
            }
            assert record != null;
            if (id != record.getId()) {
                System.out.println("Error. Can't find record with this id");
            }
        }
    private static void recordInputs (Scanner scanner) {
        int chooseRecords = chooseRecords(scanner);
        switch (chooseRecords) {
            case 1 -> budget.addRecord(newIncomeRecord(scanner));
            case 2 -> budget.addRecord(newExpensesRecord(scanner));
            default -> System.out.println("Choose from given options");
        }
    }

    private static void printRecords(ArrayList<Record> records){
            for(Record record : records){
                System.out.println(record);
            }
    }

        private static void receiveRecords (Scanner scanner){
            int chooseRecords = chooseRecords(scanner);
            switch (chooseRecords) {
                case 1 -> printRecords(budget.receiveAllRecords());
                case 2 -> printRecords(budget.receiveAllIncomeRecords());
                case 3 -> printRecords(budget.receiveAllExpenseRecords());
                default -> { System.out.println("Choose only from given numbers");
                mainMenu(scanner);
                }
            }
        }
        private static int chooseRecords (Scanner scanner){
            System.out.println(
                    "Choose: \n" +
                            "1 - Income records\n" +
                            "2 - Expense records");
            return numberChoice(scanner, 1, 2, 3);
        }


        public static int numberChoice (Scanner scanner,int...possibleValues){
            int chosen = 0;
            boolean notInput = true;
            while (notInput) {
                String inputData = scanner.next();
                try {
                    chosen = Integer.parseInt(inputData);
                    if (possibleValues.length == 0 || isNumberOnList(possibleValues, chosen)) {
                        notInput = false;
                    } else {
                        System.out.println("Unknown command");
                        mainMenu(scanner);
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Error...please input a number");
                    break;
                }
        }
            return chosen;
        }

        private static boolean isNumberOnList ( final int[] numberList, final int number){
            boolean result = false;
            for (int i : numberList) {
                if (i == number) {
                    result = true;
                    break;
                }
            }
            return result;
        }

        private static void printBalance (Budget budget){
            float balance = budget.balance();
            System.out.println(String.format("Balance: %.2fEur%n", balance));
        }
}
