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
        String report;
        List<SalesRecord> salesRecords = extractSalesRecords(fileAddress);
        report = model + " Yearly Sales Report\n" + "---------------------------\n" +
                generateYearlyReport(salesRecords) +
                generateBestAndWorstReport(salesRecords, model);
        return report;
    }

    private String generateYearlyReport(List<SalesRecord> salesRecords) {
        String yearlyReport;
        Map<Integer, List<SalesRecord>> yearSalesRecordListMap = salesRecords.stream()
                .collect(Collectors.groupingBy(SalesRecord::getYear));
        yearlyReport = yearSalesRecordListMap.entrySet().stream().
                map(entry -> entry.getKey().toString() + " -> "
                        + entry.getValue().stream().mapToInt(SalesRecord::getSale).sum())
                .collect(Collectors.joining("\n")) + "\n";
        return yearlyReport;
    }

    private String generateBestAndWorstReport(List<SalesRecord> salesRecords, String model) {
        Comparator<SalesRecord> salesRecordComparator = Comparator.comparingInt(SalesRecord::getSale);
        final String[] report = new String[2];
        salesRecords.stream().max(salesRecordComparator).
                ifPresent(salesRecord -> {
                            report[0] = "The best month for " +
                                    model +
                                    " was: " +
                                    salesRecord.getYearMonth().format(DateTimeFormatter.ofPattern("yyyy-MM")) +
                                    "\n";
                        }
                );
        salesRecords.stream().min(salesRecordComparator).
                ifPresent(salesRecord -> {
                            report[1] = "The worst month for " +
                                    model +
                                    " was: " +
                                    salesRecord.getYearMonth().format(DateTimeFormatter.ofPattern("yyyy-MM")) +
                                    "\n";
                        }
                );
        return (report[0] + report[1]);
    }
}
