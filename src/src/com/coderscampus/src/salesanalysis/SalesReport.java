package src.com.coderscampus.src.salesanalysis;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesReport {
    String model;
    String fileAddress;
    List<SalesRecord> salesRecords;

    public SalesReport(String model, String fileAddress) {
        this.model = model;
        this.fileAddress = fileAddress;
    }

    public String toString() {
        StringBuilder report = new StringBuilder();
        report.append(model).append(" Yearly Sales Report\n");
        report.append("---------------------------\n");
        extractSalesRecords();
        report.append(generateYearlyReport());
        report.append(generateBestAndWorstReport());
        return report.toString();
    }

    private void extractSalesRecords() {
        FileService fileService = new FileService();
        try {
            salesRecords = fileService.readSalesRecordFile(fileAddress);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateYearlyReport() {
        StringBuilder yearlyReport = new StringBuilder();

        Map<Integer, List<SalesRecord>> yearSalesRecordListMap = salesRecords.stream()
                .collect(Collectors.groupingBy(SalesRecord::getYear));

        yearlyReport.append(yearSalesRecordListMap.entrySet().stream().
                map(entry -> entry.getKey().toString() + " -> "
                        + entry.getValue().stream().mapToInt(SalesRecord::getSale).sum())
                .collect(Collectors.joining("\n")));

        return yearlyReport.append("\n").toString();
    }


    private String generateBestAndWorstReport() {
        Comparator<SalesRecord> salesRecordComparator = Comparator.comparingInt(SalesRecord::getSale);
        StringBuilder report = new StringBuilder();
        salesRecords.stream().max(salesRecordComparator).
                ifPresent(salesRecord -> {
                            (report.append("The best month for ").
                                    append(model).
                                    append(" was: ")).
                                    append(salesRecord.getYearMonth().format(DateTimeFormatter.ofPattern("yyyy-MM"))).
                                    append("\n");
                        }
                );
        salesRecords.stream().min(salesRecordComparator).
                ifPresent(salesRecord -> {
                            (report.append("The worst month for ").
                                    append(model).
                                    append(" was: ")).
                                    append(salesRecord.getYearMonth().format(DateTimeFormatter.ofPattern("yyyy-MM"))).
                                    append("\n");
                        }
                );
        return report.toString();
    }
}
