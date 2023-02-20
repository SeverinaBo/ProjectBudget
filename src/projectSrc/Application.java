package src.projectSrc;



import java.io.IOException;
import java.time.LocalDate;
import java.util.*;




public class Application  {

   public static ArrayList<Record> records = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    static Budget budget = new Budget();

    static Record record  = new Record();

    public static void main(String[] args) throws IOException {
        int input = scanner.nextInt();
        while (true) {
            mainMenu(scanner);
            switch (input) {
                case 1 -> newIncomeRecord(scanner);
                case 2 -> newExpensesRecord(scanner);
                case 3 -> {
                    System.out.println("All records: ");
                    printRecords(budget.receiveAllRecords());
                    printRecords(budget.receiveAllIncomeRecords());
                    printRecords(budget.receiveAllExpenseRecords());
                }
                case 4 -> editRecord(scanner, record, budget);
                case 5 -> printBalance(budget);
                case 6 -> budget.receiveAllRecords().addAll(Failas.printData());
                case 7 -> Failas.saveData((ArrayList<Record>) budget.receiveAllRecords());
                case 8 -> deleteRecord(scanner, budget);
                case 9 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid choice.");
                }
            }
scanner.close();
        }
    }

    private static int mainMenu(Scanner scanner){
        System.out.println
                ("""
                        [1] - new income record
                        [2] - new expenses record
                        [3] - print all records
                        [4] - edit records
                        [5] - balance
                        [6] - print from file
                        [7] - save to file
                        [8] - delete record
                        [9] - close the program""");
        return numberChoice(scanner, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    private static Record newIncomeRecord(Scanner scanner) {
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

        System.out.println("Income information:");
        incomeRecord.setInfo(scanner.next());
        incomeRecord.setDate(LocalDate.now());

        System.out.println(incomeRecord);
        return incomeRecord;
    }

    private static Record newExpensesRecord(Scanner scanner){
        ExpenseRecord expensesRecord = new ExpenseRecord();

        System.out.println("Expense amount: ");
        expensesRecord.setAmount(scanner.nextDouble());

        System.out.println("Expense category: ");
        try {
            for (ExpensesCategory eC : ExpensesCategory.values()) {
                System.out.println(eC);
            }} catch (Exception e) {
            throw new RuntimeException(e);
        }

        expensesRecord.setCategory(ExpensesCategory.expensesCategoryByNumber(scanner.nextInt()));

        System.out.println("Expense information:");
        expensesRecord.setInfo(textInput(scanner));
        expensesRecord.setDate(LocalDate.now());

        System.out.println(expensesRecord);
        return expensesRecord;
    }
    private static double amountInput(Scanner scanner) {

        boolean notInput = true;

        double amount = scanner.nextDouble();
        while (notInput) {
            String inputData = scanner.next();
            try {
                amount = Double.parseDouble(inputData);
                notInput = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Error...input a number");
            }
        }
        return amount;
    }

    private static void deleteRecord(Scanner scanner, Budget budget){
        System.out.println("Input Id");
        long chosenId = numberChoice(scanner);
        budget.deleteRecord(chosenId);
    }

    private static void editRecord(Scanner scanner, Record record, Budget budget){
        System.out.println("Input Id");
        long chosenId = numberChoice(scanner);
         record = budget.printRecordById(chosenId);

        if (record != null) {
            System.out.println("Amount: " + record.getAmount() + ". Edit: [Y] - yes, [N] - no");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("y"))
                record.setAmount(amountInput(scanner));
            System.out.println("Category: " + ((IncomeRecord) record).getCategory().getIncomeCategoryName() + ". Edit: [Y] - yes, [N] - no");
            answer = scanner.next();

            if (answer.equalsIgnoreCase("y")) {
                for (IncomeCategory iC : IncomeCategory.values()) {
                    System.out.println(iC);
                }
                //  (record.setIncomeCategory(incomeCategoryChoice(scanner)));

                System.out.println("Category: " + ((ExpenseRecord)record).getCategory().getExpensePurpose() + ". Edit: [Y] - yes, [N] - no");
                answer = scanner.next();
                if (answer.equalsIgnoreCase("y")) {
                    for (ExpensesCategory eC : ExpensesCategory.values()) {
                        System.out.println(eC);
                    }
                    //     (record.setExpensesCategory(expensesCategoryChoice(scanner)));
                }
            }

            System.out.println("Extra info: " + record.getAmount() + ". Edit: [Y] - yes, [N] - no");
            answer = scanner.next();
            if (answer.equals("Y"))
                record.setInfo(textInput(scanner));
            record.setInfo(textInput(scanner));

            budget.renewRecord(record);
        } else {
            System.out.println("Record not found");
        }

    }



    private static void printRecords(List <Record> incomeExpenseArr) {
        System.out.println("______________________________________");
        for (Record record : incomeExpenseArr) {
            System.out.println(record);
        }
        System.out.println("______________________________________");
    }




    public static int numberChoice(Scanner scanner, int...possibleValues){
        int  chosen = 0;
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

    private static boolean isNumberOnList(final int[] numberList, final int number){
        boolean result = false;
        for (int i : numberList) {
            if (i == number) {
                result = true;
                break;
            }
        }
        return result;
    }


    private static ExpensesCategory expensesCategoryChoice(Scanner scanner){
        ExpensesCategory expensesCategory = null;

        boolean notInput = true;

        while (notInput) {
            String inputData = scanner.next();
            try {
                int number = Integer.parseInt(inputData);
                expensesCategory = ExpensesCategory.expensesCategoryByNumber(number);
                if (expensesCategory != null) {
                    notInput = false;
                } else {
                    System.out.println("Expenses category not found");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error...please input a number");
            }
        }
        return expensesCategory;
    }


    private static IncomeCategory incomeCategoryChoice(Scanner scanner){
        IncomeCategory incomeCategory = null;
        boolean notInput = true;
        while (notInput) {
            String inputData = scanner.next();
            try {
                int number = Integer.parseInt(inputData);
                incomeCategory = IncomeCategory.incomeCategoryByNumber(number);
                if (incomeCategory != null) {
                    notInput = false;
                } else {
                    System.out.println("Input category not found");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error...please input a number");
            }
        }
        return incomeCategory;
    }

    private static String textInput(Scanner scanner){
        return scanner.next();
    }

    private static void printBalance(Budget budget){
        double balance = budget.calculateBalance();
        System.out.printf(String.format("Balance: %.2fEur%n", balance));
    }


}
