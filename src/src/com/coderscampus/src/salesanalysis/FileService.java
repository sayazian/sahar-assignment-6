package src.com.coderscampus.src.salesanalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileService {
    public ArrayList<SalesReport> readFile(String filePath) throws FileNotFoundException {
        ArrayList<SalesReport> salesReports = new ArrayList<>();
        File file = new File(filePath);
        Scanner input = new Scanner(file);
        input.nextLine();
        while(input.hasNextLine()) {
            String line = input.nextLine();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM-yy");
            String[] record = new String[2];
            record[0] = line.split(",")[0];
            record[1] = line.split(",")[1];
            YearMonth yearMonth = YearMonth.parse(record[0], dateTimeFormatter);
            int sale = Integer.parseInt(record[1]);
            SalesReport salesReport = new SalesReport(yearMonth, sale);
            salesReports.add(salesReport);
        }
        return salesReports;
    }

}
