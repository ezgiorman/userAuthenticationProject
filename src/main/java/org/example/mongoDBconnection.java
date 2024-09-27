package org.example;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class mongoDBconnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void connect() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("user_authentication");
        System.out.println("Connected to MongoDB.");
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void close() {
        mongoClient.close(); // Bağlantıyı kapat
        System.out.println("MongoDB connection is over.");
    }
}
