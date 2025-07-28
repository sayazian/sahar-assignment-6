package src.com.coderscampus.src.salesanalysis;

import java.io.FileNotFoundException;
import java.util.List;

public class TeslaSalesReporter {
    public static void main(String[] args) throws FileNotFoundException {
        String model3 = "model3.csv";
        String modelS = "modelS.csv";
        String modelX = "modelX.csv";
        FileService fileService = new FileService();
        SalesReportService salesReportService = new SalesReportService();
        List<SalesReport> model3SalesReport = fileService.readFile(model3);
        List<SalesReport> modelSSalesReport = fileService.readFile(modelS);
        List<SalesReport> modelXSalesReport = fileService.readFile(modelX);

        System.out.println(salesReportService.generateReport(model3SalesReport, "Model 3"));
        System.out.println(salesReportService.generateReport(modelSSalesReport, "Model S"));
        System.out.println(salesReportService.generateReport(modelXSalesReport, "Model X"));

    }
}
