package src.com.coderscampus.src.salesanalysis;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {
    public List<SalesRecord> readSalesRecordFile(String fileAddress) throws FileNotFoundException {
        File file = new File(fileAddress);
        Scanner input = new Scanner(file);
        List<SalesRecord> salesRecords = new ArrayList<>();
        input.nextLine();
        String[] lineParts;
        while (input.hasNext()) {
            lineParts = input.nextLine().split(",");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM-yy");
            YearMonth yearMonth = YearMonth.parse(lineParts[0], dateTimeFormatter);
            int sale = Integer.parseInt(lineParts[1]);
            salesRecords.add(new SalesRecord(yearMonth, sale));
        }
        return salesRecords;
    }
}
