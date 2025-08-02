package src.com.coderscampus.src.salesanalysis.domain;

import java.time.YearMonth;

public class SalesRecord {
    private YearMonth yearMonth;
    private int sale;

    public SalesRecord(YearMonth yearMonth, int sale) {
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

    public Integer getYear() {
        return yearMonth.getYear();
    }

}

