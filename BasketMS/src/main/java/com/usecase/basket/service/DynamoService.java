package com.usecase.basket.service;

import com.usecase.basket.entity.Basket;
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
public class DynamoService {
    private DynamoDbTable<Basket> getDbClient() {
        Region region = Region.US_EAST_1;
        DynamoDbClient ddb = DynamoDbClient.builder().region(region)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create()).build();
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient
                .builder().dynamoDbClient(ddb).build();
        return enhancedClient.table("Basket", TableSchema.fromBean(Basket.class));
    }

    public List<Basket> getAllBasketsList() {
        try {
            return getDbClient().scan().items().stream().collect(Collectors.toList());
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Done");
        return null;
    }

    public Basket getBasketDetails(String BasketId) {
        Basket basket = new Basket();
        try {
            //Get the Key object.
            Key key = Key.builder().partitionValue(BasketId).build();
            // Get the item by using the key.
            basket = getDbClient().getItem(key);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        return basket;
    }

    public String injectDynamoItem(Basket basket) {
        try {
            getDbClient().putItem(basket);
            return basket.getBasketId();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "error in updating table";
    }

}
