package org.usecase.scenario1;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVHandler {
    public List<Product> readDataFromCSV(ResponseInputStream<GetObjectResponse> object) {
        Map<String, String> mapping = new
                HashMap<>();
        mapping.put("ProductId", "productId");
        mapping.put("ProductType", "productType");
        mapping.put("ProductName", "productName");
        mapping.put("Price", "price");
        List<Product> productList = null;
        HeaderColumnNameTranslateMappingStrategy<Product> strategy =
                new HeaderColumnNameTranslateMappingStrategy<>();
        strategy.setType(Product.class);
        strategy.setColumnMapping(mapping);

        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new InputStreamReader(object));
            // csvReader = new CSVReader(new FileReader("C:\\Users\\nikhilesh.macherla\\Downloads\\products.csv"));
            CsvToBean<Product> csvToBean = new CsvToBean<>();
            csvToBean.setMappingStrategy(strategy);
            csvToBean.setCsvReader(csvReader);
            productList = csvToBean.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
