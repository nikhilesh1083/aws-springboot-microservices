package org.usecase.scenario1;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DynamoService {

    private DynamoDbTable<Product> getDbClient() {
        Region region = Region.US_EAST_1;
        DynamoDbClient ddb = DynamoDbClient.builder().region(region).build();
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient
                .builder().dynamoDbClient(ddb).build();
        return enhancedClient.table("Products", TableSchema.fromBean(Product.class));
    }

    public String injectDynamoItem(Product item) {
        try {
            getDbClient().putItem(item);
            return "Item inserted Successfully";
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    // Get a single item from the Work table based on the Key.
    public Product getItem(String idValue) {
        Product greetingItem = null;
        try {
            //Get the Key object.
            Key key = Key.builder().partitionValue(idValue).build();
            // Get the item by using the key.
            greetingItem = getDbClient().getItem(key);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        return greetingItem;
    }

    // Retrieves items from the DynamoDB table.
    public List<Product> getListItems() {
        try {
            return getDbClient().scan().items().stream().collect(Collectors.toList());
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Done");
        return null;
    }

    // Update the column by using the Enhanced Client.
    public String updateItem(Product items) {
        try {
            //Get the Key object.
            Key key = Key.builder().partitionValue(items.getProductId()).build();
            // Get the item by using the key.
            Product work = getDbClient().getItem(key);
            if (Objects.nonNull(work)) {
                getDbClient().updateItem(items);
                return "The item was successfully Updated";
            } else
                return "No Records present with given data";
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String deleteItem(String id) {
        try {
            Key key = Key.builder().partitionValue(id).build();
            Product items = getDbClient().getItem(key);
            if (Objects.nonNull(items)) {
                getDbClient().deleteItem(key);
                return "The item was successfully deleted";
            } else
                return "No Records present with given data";
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
