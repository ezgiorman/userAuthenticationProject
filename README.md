# User Authentication System

This is a Java-based user authentication system that allows user registration, login, logout, and password management. MongoDB is used as the database, and BCrypt is used for secure password hashing.

## Features

- User registration with password validation (min 8 chars, 1 uppercase, 1 lowercase, 1 digit, 1 special character).
- Secure password hashing using BCrypt.
- User login with session management.
- User logout with session termination.
- Session timeout for automatic logout after inactivity.
- Error handling for weak passwords, failed login attempts, and more.

## Prerequisites

To run this project locally, you need to have the following installed:

- Java Development Kit (JDK) 11 or higher
- MongoDB
- Git (optional)
- Maven (to manage dependencies)

## Setup Instructions

### 1. Install MongoDB

1. Go to the [MongoDB website](https://www.mongodb.com/try/download/community) and download the MongoDB Community Server.
2. Install MongoDB by following the instructions for your operating system.
3. After installation, start the MongoDB service:
   - On Windows, run MongoDB from the **Services** app or use the command:
     ```
     net start MongoDB
     ```
   - On macOS or Linux, use the following command:
     ```
     brew services start mongodb-community@6.0
     ```
4. Make sure MongoDB is running by connecting via the MongoDB shell:
