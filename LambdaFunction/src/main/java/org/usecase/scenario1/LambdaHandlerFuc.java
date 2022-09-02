package org.usecase.scenario1;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LambdaHandlerFuc implements RequestHandler<Map<String, String>, String> {
    @Override
    public String handleRequest(Map<String, String> stringStringMap, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Lambda Execution Started");
        CSVHandler csvHandler = new CSVHandler();
        DynamoService dynamoService = new DynamoService();
        S3Service s3Service = new S3Service();
        logger.log("Bucket Name: "
                + stringStringMap.get("bucketName") + "FileName : products.csv ");
        ResponseInputStream<GetObjectResponse> csvFile = s3Service.
                getCSVFileByteArray(stringStringMap.get("bucketName"), "products.csv");
        if (Objects.nonNull(csvFile)) {
            List<Product> products = csvHandler.readDataFromCSV(csvFile);
            if (!products.isEmpty()) {
                for (Product product : products)
                    dynamoService.injectDynamoItem(product);
                logger.log("No.Of Records inserted to DynamoDB:: " + products.size());
            }
        }
        logger.log("Lambda Execution Started");
        return "Task Completed";
    }
}
