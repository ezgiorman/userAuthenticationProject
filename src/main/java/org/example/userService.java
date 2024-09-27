package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

import static com.mongodb.client.model.Filters.eq;

public class userService {

    private final MongoDatabase database;

    public userService() {
        this.database = mongoDBconnection.getDatabase();
    }

    public String registerUser(String username, String password) {
        if (!PasswordValidator.isValidPassword(password)) {
            return "\n" +
                    "The password must be at least 8 characters and contain at least one uppercase letter, one lowercase letter, one number and one special character.";
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        MongoCollection<Document> usersCollection = database.getCollection("users");
        Document user = new Document("username", username)
                .append("password", hashedPassword);
        usersCollection.insertOne(user);
        return "Registered.";
    }

    public String loginUser(String username, String password) {
        MongoCollection<Document> usersCollection = database.getCollection("users");
        Document user = usersCollection.find(eq("username", username)).first();

        if (user != null && BCrypt.checkpw(password, user.getString("password"))) {
            return "Successfully logged in.";
        }
        return "Invalid username or password!";
    }
}
