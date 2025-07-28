package src.com.coderscampus.src.salesanalysis;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesReportService
{
    public String generateReport(List<SalesReport> salesReports, String model) {
        StringBuilder report = new StringBuilder();
        report.append(model).append(" Yearly Sales Report \n").append("---------------------------\n");

//        int sales17 = (int)salesReports.stream().filter(x -> x.getYearMonth().getYear() == 2017).count();

        Map<Integer, List<SalesReport>> salesGroupedByYear = salesReports.stream().
                collect(Collectors.groupingBy(x -> x.getYearMonth().getYear()));

        mapListOfReportsToSumOfSales(salesGroupedByYear).entrySet().stream().forEach(
                entry -> report.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n")
        );






//        report += "2017 -> " + sales17 + "\n";
//        report += "2018 -> " + sales18 + "\n";
//        report += "2019 -> " + sales19 + "\n";
////        LocalDate bestLocalDate = salesReports.stream().flatMap(x -> x);
////        report += "The best month for " + model + " was: " + yyyy-MM + "\n" ;
//
////        The best month for Model 3 was: yyyy-MM
////        The worst month for Model 3 was: yyyy-MM

        return report.toString();
    }

    public int getTotalSaleOfYear(List<SalesReport> salesReportsList) {
        return salesReportsList.stream().map(SalesReport::getSale).
                collect(Collectors.summingInt(x -> Integer.valueOf(x)));
    }

    public Map<Integer, Integer> mapListOfReportsToSumOfSales (Map<Integer, List<SalesReport>> input) {
        return input.entrySet().stream().
                collect(Collectors.toMap(Map.Entry::getKey, entry -> getTotalSaleOfYear(entry.getValue())));
    }

    public Map<Integer, SalesReport> foo (Map<Integer, SalesReport> input) {
        return input.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey , Map.Entry::getValue));
    }
}
