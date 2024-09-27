package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mongoDBconnection.connect();
        userService userService = new userService();
        sessionService sessionService = new sessionService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, please do your selection: register, login, logout, exit");

        while (true) {
            System.out.print("selection: ");
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

                case "exit":
                    mongoDBconnection.close();
                    return;

                default:
                    System.out.println("Invalid selection, please try to enter again.");
            }
        }
    }
}
