package com.pragma.integration.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import org.openqa.selenium.WebDriver;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.regions.Region;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DynamoDBInteraction implements Interaction {

    private final String operation;
    private final String tableName;
    private final Map<String, AttributeValue> item;
    private final String keyName;
    private final String keyValue;
    private Actor actor;

    public DynamoDBInteraction(String operation, String tableName, Map<String, AttributeValue> item, String keyName, String keyValue) {
        this.operation = operation;
        this.tableName = tableName;
        this.item = item;
        this.keyName = keyName;
        this.keyValue = keyValue;
    }

    public static DynamoDBInteractionBuilder withTable(String tableName) {
        return instrumented(DynamoDBInteractionBuilder.class, tableName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        this.actor = actor;
        DynamoDbClient dynamoDBClient = getDynamoDBClient(actor);

        switch (operation.toUpperCase()) {
            case "PUT":
                performPutItem(dynamoDBClient);
                break;
            case "GET":
                performGetItem(dynamoDBClient);
                break;
            case "DELETE":
                performDeleteItem(dynamoDBClient);
                break;
            default:
                throw new IllegalArgumentException("Operación no soportada: " + operation);
        }
    }

    private void performPutItem(DynamoDbClient client) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        client.putItem(request);
    }

    private void performGetItem(DynamoDbClient client) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(keyName, AttributeValue.builder().s(keyValue).build());

        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        GetItemResponse response = client.getItem(request);
        
        if (response.hasItem()) {
            actorStoreItem(response.item());
        }
    }

    private void performDeleteItem(DynamoDbClient client) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(keyName, AttributeValue.builder().s(keyValue).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        client.deleteItem(request);
    }

    private DynamoDbClient getDynamoDBClient(Actor actor) {
        String endpoint = System.getenv().getOrDefault("DYNAMODB_ENDPOINT", "http://localhost:8000");
        String region = System.getenv().getOrDefault("AWS_REGION", "us-east-1");

        return DynamoDbClient.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(() -> software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create(
                        System.getenv().getOrDefault("AWS_ACCESS_KEY_ID", "dummy"),
                        System.getenv().getOrDefault("AWS_SECRET_ACCESS_KEY", "dummy")
                ))
                .build();
    }

    private void actorStoreItem(Map<String, AttributeValue> item) {
        if (actor != null) {
            actor.remember("lastRetrievedItem", item);
        }
    }

    public static class DynamoDBInteractionBuilder {
        private String tableName;
        private String operation;
        private Map<String, AttributeValue> item = new HashMap<>();
        private String keyName;
        private String keyValue;

        public DynamoDBInteractionBuilder(String tableName) {
            this.tableName = tableName;
        }

        public DynamoDBInteractionBuilder withOperation(String operation) {
            this.operation = operation;
            return this;
        }

        public DynamoDBInteractionBuilder withItem(Map<String, AttributeValue> item) {
            this.item = item;
            return this;
        }

        public DynamoDBInteractionBuilder withKey(String keyName, String keyValue) {
            this.keyName = keyName;
            this.keyValue = keyValue;
            return this;
        }

        public DynamoDBInteraction build() {
            return new DynamoDBInteraction(operation, tableName, item, keyName, keyValue);
        }
    }
}