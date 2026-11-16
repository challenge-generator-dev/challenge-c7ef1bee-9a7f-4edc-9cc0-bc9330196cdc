package com.pragma.integration.config;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

public class DynamoDBConfig {

    private static final String DYNAMODB_LOCAL_ENDPOINT = System.getenv("DYNAMODB_ENDPOINT");
    private static final String AWS_REGION = System.getenv().getOrDefault("AWS_REGION", "us-east-1");
    private static final String AWS_ACCESS_KEY = System.getenv().getOrDefault("AWS_ACCESS_KEY_ID", "test");
    private static final String AWS_SECRET_KEY = System.getenv().getOrDefault("AWS_SECRET_ACCESS_KEY", "test");

    private static DynamoDbClient dynamoDbClient;
    private static DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public static DynamoDbClient getDynamoDbClient() {
        if (dynamoDbClient == null) {
            dynamoDbClient = buildDynamoDbClient();
        }
        return dynamoDbClient;
    }

    public static DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        if (dynamoDbEnhancedClient == null) {
            dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(getDynamoDbClient())
                    .build();
        }
        return dynamoDbEnhancedClient;
    }

    private static DynamoDbClient buildDynamoDbClient() {
        var builder = DynamoDbClient.builder()
                .region(Region.of(AWS_REGION))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(AWS_ACCESS_KEY, AWS_SECRET_KEY)
                ));

        // TODO(reto): Configurar endpoint personalizado para DynamoDB local
        // El endpoint debe obtenerse de la variable de entorno DYNAMODB_ENDPOINT
        // Si no está configurada, usar valor por defecto para desarrollo local

        if (DYNAMODB_LOCAL_ENDPOINT != null && !DYNAMODB_LOCAL_ENDPOINT.isBlank()) {
            builder.endpointOverride(URI.create(DYNAMODB_LOCAL_ENDPOINT));
        }

        return builder.build();
    }

    public static void reset() {
        if (dynamoDbClient != null) {
            dynamoDbClient.close();
        }
        if (dynamoDbEnhancedClient != null) {
            dynamoDbEnhancedClient.close();
        }
        dynamoDbClient = null;
        dynamoDbEnhancedClient = null;
    }
}