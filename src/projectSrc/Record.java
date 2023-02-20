package src.projectSrc;

import java.time.LocalDate;

public class Record {
    private static long counter = 0;
    private long id;
    private double amount;
    private LocalDate date;
    private String info;

    public Record () {
        this.id = counter++;
    }
    public Record (double amount, LocalDate date, String info) {
        this.amount = amount;
        this.date = date;
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format("%d, Amount = %.2f, Date: %s, Info: %s", id, amount, date, info);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Record && id == ((Record)obj).getId()) {
            return true;
        } else {
            return false;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String toCsv() {
        return String.format("%d,%.2f,%s,%s", id, amount, date, info);
    }
}