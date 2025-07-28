package src.com.coderscampus.src.salesanalysis;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class SalesReport {
    YearMonth yearMonth;
    int sale;

    public SalesReport(YearMonth yearMonth, int sale) {
        this.yearMonth = yearMonth;
        this.sale = sale;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String toString() {
        return yearMonth.format(DateTimeFormatter.ofPattern("MM-yy")) + " " + Integer.toString(sale);
    }
}
