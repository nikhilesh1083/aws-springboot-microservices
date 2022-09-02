package com.usecase.product.service;

import com.usecase.product.entity.Product;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DynamoDBService {
    private DynamoDbTable<Product> getDbClient() {
        Region region = Region.US_EAST_1;
        DynamoDbClient ddb = DynamoDbClient.builder().region(region).credentialsProvider(EnvironmentVariableCredentialsProvider.create()).build();
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient
                .builder().dynamoDbClient(ddb).build();
        return enhancedClient.table("Products", TableSchema.fromBean(Product.class));
    }

    public List<Product> getAllProductsList() {
        try {
            return getDbClient().scan().items().stream().collect(Collectors.toList());
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Done");
        return null;
    }

    public Product getProductDetails(String productId) {
        Product product = new Product();
        try {
            //Get the Key object.
            Key key = Key.builder().partitionValue(productId).build();
            // Get the item by using the key.
            product = getDbClient().getItem(key);
        } catch (DynamoDbException e) {
            e.printStackTrace();
        }
        return product;
    }
}
