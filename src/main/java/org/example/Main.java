package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mongoDBconnection.connect();
        userService userService = new userService();
        sessionService sessionService = new sessionService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, please do your selection: register, login, logout, forget password, reset password, exit");

        while (true) {
            System.out.print("Selection: ");
            String command = scanner.nextLine();

            switch (command) {
                case "register":
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    String registerMessage = userService.registerUser(username, password);
                    System.out.println(registerMessage);
                    break;

                case "login":
                    System.out.print("Username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginPassword = scanner.nextLine();
                    String loginMessage = userService.loginUser(loginUsername, loginPassword);
                    System.out.println(loginMessage);
                    break;

                case "logout":
                    System.out.print("Enter your session: ");
                    String sessionId = scanner.nextLine();
                    sessionService.logout(sessionId);
                    System.out.println("Session is closed successfully.");
                    break;

                case "forget password":
                    System.out.print("Enter your username: ");
                    String forgetPasswordUsername = scanner.nextLine();
                    String resetLink = userService.requestPasswordReset(forgetPasswordUsername);
                    System.out.println(resetLink);
                    break;

                case "reset password":
                    System.out.print("Enter reset token: ");
                    String resetToken = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    String resetPasswordMessage = userService.resetPassword(resetToken, newPassword);
                    System.out.println(resetPasswordMessage);
                    break;

                case "exit":
                    mongoDBconnection.close();
                    return;

                default:
                    System.out.println("Invalid selection, please try again.");
            }
        }
    }
}
