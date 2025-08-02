package src.com.coderscampus.src.salesanalysis.service;
import src.com.coderscampus.src.salesanalysis.domain.SalesRecord;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesRecordService {

    private List<SalesRecord> extractSalesRecords(String fileAddress) {
        FileService fileService = new FileService();
            return fileService.readSalesRecordFile(fileAddress);
    }

    public String generateReport(String model, String fileAddress) {
        StringBuilder report = new StringBuilder();
        List<SalesRecord> salesRecords = extractSalesRecords(fileAddress);
        report.append(model).append(" Yearly Sales Report\n");
        report.append("---------------------------\n");
        extractSalesRecords(fileAddress);
        report.append(generateYearlyReport(salesRecords));
        report.append(generateBestAndWorstReport(salesRecords, model));
        return report.toString();
    }

    private String generateYearlyReport(List<SalesRecord> salesRecords) {
        StringBuilder yearlyReport = new StringBuilder();
        Map<Integer, List<SalesRecord>> yearSalesRecordListMap = salesRecords.stream()
                .collect(Collectors.groupingBy(SalesRecord::getYear));

        yearlyReport.append(yearSalesRecordListMap.entrySet().stream().
                map(entry -> entry.getKey().toString() + " -> "
                        + entry.getValue().stream().mapToInt(SalesRecord::getSale).sum())
                .collect(Collectors.joining("\n")));
        return yearlyReport.append("\n").toString();
    }

    private String generateBestAndWorstReport(List<SalesRecord> salesRecords, String model) {
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
