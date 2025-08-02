package src.com.coderscampus.src.salesanalysis;
import src.com.coderscampus.src.salesanalysis.service.SalesRecordService;

public class TeslaSalesReporter {
    public static void main(String[] args) {
        String model3FileAddress = "model3.csv";
        String modelSFileAddress = "modelS.csv";
        String modelXFileAddress = "modelX.csv";

        SalesRecordService salesRecordService = new SalesRecordService();

        System.out.println(salesRecordService.generateReport("Model 3", model3FileAddress));
        System.out.println(salesRecordService.generateReport("Model S", modelSFileAddress));
        System.out.println(salesRecordService.generateReport("Model X", modelXFileAddress));
    }
}
