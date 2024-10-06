package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class userService {

    private final MongoDatabase database;

    public userService() {
        this.database = mongoDBconnection.getDatabase();
    }


    public String registerUser(String username, String password) {
        if (!PasswordValidator.isValidPassword(password)) {
            return "\nThe password must be at least 8 characters and contain at least one uppercase letter, one lowercase letter, one number, and one special character.";
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        MongoCollection<Document> usersCollection = database.getCollection("users");
        Document user = new Document("username", username)
                .append("password", hashedPassword);
        usersCollection.insertOne(user);
        return "User successfully registered.";
    }


    public String loginUser(String username, String password) {
        MongoCollection<Document> usersCollection = database.getCollection("users");
        Document user = usersCollection.find(eq("username", username)).first();

        if (user != null && BCrypt.checkpw(password, user.getString("password"))) {
            return "Successfully logged in.";
        }
        return "Invalid username or password!";
    }


    public String requestPasswordReset(String username) {
        MongoCollection<Document> usersCollection = database.getCollection("users");
        Document user = usersCollection.find(eq("username", username)).first();

        if (user != null) {

            String resetToken = UUID.randomUUID().toString();

            usersCollection.updateOne(eq("username", username),
                    new Document("$set", new Document("resetToken", resetToken)));


            String resetLink = "http://localhost:8080/reset-password?token=8a67642e-50d0-4815-ba42-c4d1a0bed62a\n" + resetToken;
            return "Password reset link: " + resetLink + "\nPlease use this link to reset your password.";
        }
        return "User not found!";
    }


    public String resetPassword(String resetToken, String newPassword) {
        if (!PasswordValidator.isValidPassword(newPassword)) {
            return "The password must be at least 8 characters and contain at least one uppercase letter, one lowercase letter, one number, and one special character.";
        }

        MongoCollection<Document> usersCollection = database.getCollection("users");
        Document user = usersCollection.find(eq("resetToken", resetToken)).first();

        if (user != null) {

            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());


            usersCollection.updateOne(eq("resetToken", resetToken),
                    new Document("$set", new Document("password", hashedPassword))
                            .append("$unset", new Document("resetToken", "")));

            return "Password successfully reset.";
        }
        return "Invalid or expired reset token!";
    }
}
