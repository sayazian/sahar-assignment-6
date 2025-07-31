package src.com.coderscampus.src.salesanalysis;

public class TeslaSalesReporter {
    public static void main(String[] args) {
        String model3FileAddress = "model3.csv";
        String modelSFileAddress = "modelS.csv";
        String modelXFileAddress = "modelX.csv";

        System.out.println(new SalesReport("Model 3", model3FileAddress));
        System.out.println(new SalesReport("Model S", modelSFileAddress));
        System.out.println(new SalesReport("Model X", modelXFileAddress));
    }
}
