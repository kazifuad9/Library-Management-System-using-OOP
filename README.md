# Library Management System using OOP

A comprehensive library management system built in Java that demonstrates Object-Oriented Programming principles. This console-based application allows librarians and members to manage books, loans, and more.

## Features

### For Members
- **User Registration & Authentication**: Secure account creation and login
- **Book Browsing**: View and search available books in the library
- **Borrowing System**: Borrow books with automatic due date tracking
- **Return Management**: Return books and view borrowing history
- **Fine Calculation**: Automatic calculation of overdue fines
- **Account Management**: Update passwords and view personal information

### For Librarians
- **Book Management**: Add, remove, and search books in the catalog
- **Member Management**: Add new members and manage existing accounts
- **Loan Tracking**: View all active loans and borrowing history
- **System Administration**: Complete oversight of library operations

## 🏗️ Technical Architecture

### Core Classes
- **Users (Abstract)**: Base class for all system users
- **Member**: Handles member-specific operations and book borrowing
- **Librarian**: Manages administrative functions
- **Book**: Represents book entities with availability tracking
- **Loan**: Manages borrowing records and due dates
- **Library (Singleton)**: Central system management with single instance pattern

## 🛠️ Technologies Used
- **Java 23**: Core programming language
- **Object-Oriented Design**: Inheritance, encapsulation, and polymorphism
- **Singleton Pattern**: Ensures single library instance
- **ArrayList Collections**: Dynamic data storage
- **Console I/O**: User interaction interface
