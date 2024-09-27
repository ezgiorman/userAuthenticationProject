package org.example;

public class PasswordValidator {

    public static boolean isValidPassword(String password) {

        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordPattern);
    }

    public static String validatePassword(String password) {
        if (!isValidPassword(password)) {
            return "Weak Password! \n" +
                    "The password must be at least 8 characters and contain at least one uppercase letter, one lowercase letter, one number and one special character.";
        }
        return "Strong password!";
    }
}
