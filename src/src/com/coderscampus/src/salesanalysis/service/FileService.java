package src.com.coderscampus.src.salesanalysis.service;

import src.com.coderscampus.src.salesanalysis.domain.SalesRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public List<SalesRecord> readSalesRecordFile(String fileAddress) {
        List<SalesRecord> salesRecords = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileAddress));
            String line;
            bufferedReader.readLine();
            String[] lineParts;
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM-yy");

            while ((line = bufferedReader.readLine()) != null) {
                lineParts = line.split(",");
                YearMonth yearMonth = YearMonth.parse(lineParts[0], dateTimeFormatter);
                int sale = Integer.parseInt(lineParts[1]);
                salesRecords.add(new SalesRecord(yearMonth, sale));
            }
        } catch (IOException e) {
            System.out.println("Something went wrong reading the file.\n" + e.getMessage());
        }
        return salesRecords;
    }
}
