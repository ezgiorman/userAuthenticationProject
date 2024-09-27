package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class sessionService {

    private final MongoDatabase database;

    public sessionService() {
        this.database = mongoDBconnection.getDatabase();
    }

    public String createSession(String username) {
        String sessionId = UUID.randomUUID().toString();
        MongoCollection<Document> sessionsCollection = database.getCollection("sessions");
        Document session = new Document("username", username)
                .append("sessionId", sessionId);
        sessionsCollection.insertOne(session);
        return sessionId;
    }

    public void logout(String sessionId) {
        MongoCollection<Document> sessionsCollection = database.getCollection("sessions");
        sessionsCollection.deleteOne(eq("sessionId", sessionId));
    }
}
