package src.projectSrc.Budget;

import java.time.LocalDate;

public class IncomeRecord extends Record {

        public final static String recordType = "RECORD INCOME ";

        private IncomeCategory category;

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public IncomeRecord() {
            super();
        }

        public IncomeRecord(double incomeAmount, LocalDate date, String info, IncomeCategory category) {
            super(incomeAmount, date, info);
            this.category = category;
        }

        @Override
        public String toString() {
            return String.format("%s, %s, Category:%s", recordType, super.toString(), category);
        }

        public IncomeCategory getCategory() {
            return category;
        }

        public void setCategory(IncomeCategory category) {
            this.category = category;
        }

        @Override
        public String toCsv() {
            return String.format("%s, %s, %s, %s", recordType, super.toCsv(), category.getIncomeNumber(), category.getIncomeCategoryName());
        }
    }


