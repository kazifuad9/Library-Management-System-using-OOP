import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = Library.getInstance();
    private static Users currentUser = null;

    public static void main(String[] args) {
        setupInitialData();

        System.out.println("=== WELCOME TO LIBRARY MANAGEMENT SYSTEM ===");

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                if (currentUser instanceof Librarian) {
                    showLibrarianMenu();
                } else if (currentUser instanceof Member) {
                    showMemberMenu();
                }
            }
        }
    }

    private static void setupInitialData() {
        // Create some sample books
        library.addBook(new Book("Java Programming", "Oracle", "978-0134685991"));
        library.addBook(new Book("Data Structures", "Robert Lafore", "978-0672324536"));
        library.addBook(new Book("Design Patterns", "Gang of Four", "978-0201633612"));
        library.addBook(new Book("Clean Code", "Robert Martin", "978-0132350884"));

        // Create sample users (pre-registered)
        Member sampleMember = new Member("Kazi fuad", "123456", "fuad");
        Librarian sampleLibrarian = new Librarian("librarian", "123456", "librarian_no_1");

        library.addUser(sampleMember);
        // Note: Library doesn't have addLibrarian, but we'll create them for login

        System.out.println("Sample data loaded!");
        System.out.println("Sample accounts:");
        System.out.println("Member - Username: fuad, Password: 123456");
        System.out.println("Librarian - Username: librarian_no_1, Password: 123456");
        System.out.println();
    }

    private static void showLoginMenu() {
        System.out.println("\n=== LOGIN MENU ===");
        System.out.println("1. Login as Member");
        System.out.println("2. Login as Librarian");
        System.out.println("3. Register as Member");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: loginAsMember(); break;
            case 2: loginAsLibrarian(); break;
            case 3: registerMember(); break;
            case 4:
                System.out.println("Thank you for using the Library System!");
                System.exit(0);
                break;
            default: System.out.println("Invalid choice!");
        }
    }

    private static void loginAsMember() {
        System.out.println("\n=== MEMBER LOGIN ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check against registered members in the library
        Member foundMember = findMemberByUsername(username);
        
        if (foundMember != null && foundMember.getPassword().equals(password)) {
            currentUser = foundMember;
            System.out.println("Login successful! Welcome, " + username);
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private static void loginAsLibrarian() {
        System.out.println("\n=== LIBRARIAN LOGIN ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Simple authentication for librarian
        if (username.equals("librarian_no_1") && password.equals("123456")) {
            currentUser = new Librarian("Librarian User", password, username);
            System.out.println("Login successful! Welcome, Librarian " + username);
        } else {
            System.out.println("Invalid librarian credentials!");
        }
    }

    private static void registerMember() {
        System.out.println("\n=== MEMBER REGISTRATION ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Member newMember = new Member(name, password, username);
        library.addUser(newMember);
        System.out.println("Registration successful! You can now login.");
    }

    private static void showMemberMenu() {
        System.out.println("\n=== MEMBER MENU ===");
        System.out.println("Welcome, " + currentUser.getUsername() + "!");
        System.out.println("1. View All Books");
        System.out.println("2. Search Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. View My Borrowed Books");
        System.out.println("6. Check Fine for a Book");
        System.out.println("7. Change Password");
        System.out.println("8. Logout");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: viewAllBooks(); break;
            case 2: searchBook(); break;
            case 3: borrowBook(); break;
            case 4: returnBook(); break;
            case 5: viewBorrowedBooks(); break;
            case 6: checkFine(); break;
            case 7: changePassword(); break;
            case 8: logout(); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private static void showLibrarianMenu() {
        System.out.println("\n=== LIBRARIAN MENU ===");
        System.out.println("Welcome, Librarian " + currentUser.getUsername() + "!");
        System.out.println("1. View All Books");
        System.out.println("2. Add New Book");
        System.out.println("3. Remove Book");
        System.out.println("4. Search Book");
        System.out.println("5. View All Members");
        System.out.println("6. View All Loans");
        System.out.println("7. Add Member");
        System.out.println("8. Remove Member");
        System.out.println("9. Change Password");
        System.out.println("10. Logout");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: viewAllBooks(); break;
            case 2: addBook(); break;
            case 3: removeBook(); break;
            case 4: searchBook(); break;
            case 5: viewAllMembers(); break;
            case 6: viewAllLoans(); break;
            case 7: addMember(); break;
            case 8: removeMember(); break;
            case 9: changePassword(); break;
            case 10: logout(); break;
            default: System.out.println("Invalid choice!");
        }
    }

    // ========== COMMON FUNCTIONS ==========
    private static void viewAllBooks() {
        System.out.println("\n=== ALL BOOKS ===");
        if (library.getBooks().isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        int index = 1;
        for (Book book : library.getBooks()) {
            System.out.println(index + ". ");
            book.bookDetails();
            System.out.println("---");
            index++;
        }
    }

    private static void searchBook() {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();

        boolean found = false;
        for (Book book : library.getBooks()) {
            if (book.title.toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Found:");
                book.bookDetails();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with title containing: " + title);
        }
    }

    private static void changePassword() {
        currentUser.setPassword();
    }

    private static void logout() {
        System.out.println("Logged out successfully!");
        currentUser = null;
    }

    // ========== MEMBER FUNCTIONS ==========
    private static void borrowBook() {
        if (!(currentUser instanceof Member)) return;
        Member member = (Member) currentUser;

        viewAllBooks();
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine();

        Book bookToBorrow = findBookByTitle(title);
        if (bookToBorrow != null) {
            member.borrowBook(bookToBorrow);
        } else {
            System.out.println("Book not found!");
        }
    }

    private static void returnBook() {
        if (!(currentUser instanceof Member)) return;
        Member member = (Member) currentUser;

        System.out.println("\n=== YOUR BORROWED BOOKS ===");
        member.borrowedBooksDetails();

        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();

        Book bookToReturn = findBookByTitle(title);
        if (bookToReturn != null) {
            member.returnBook(bookToReturn);
        } else {
            System.out.println("Book not found!");
        }
    }

    private static void viewBorrowedBooks() {
        if (!(currentUser instanceof Member)) return;
        Member member = (Member) currentUser;

        System.out.println("\n=== YOUR BORROWED BOOKS ===");
        member.borrowedBooksDetails();
    }

    private static void checkFine() {
        if (!(currentUser instanceof Member)) return;
        Member member = (Member) currentUser;

        System.out.print("Enter book title to check fine: ");
        String title = scanner.nextLine();

        Book book = findBookByTitle(title);
        if (book != null) {
            member.checkFine(book);
        } else {
            System.out.println("Book not found!");
        }
    }

    // ========== LIBRARIAN FUNCTIONS ==========
    private static void addBook() {
        if (!(currentUser instanceof Librarian)) return;
        Librarian librarian = (Librarian) currentUser;

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        Book newBook = new Book(title, author, isbn);
        librarian.addBook(newBook);
    }

    private static void removeBook() {
        if (!(currentUser instanceof Librarian)) return;
        Librarian librarian = (Librarian) currentUser;

        viewAllBooks();
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();

        Book bookToRemove = findBookByTitle(title);
        if (bookToRemove != null) {
            librarian.removeBook(bookToRemove);
        } else {
            System.out.println("Book not found!");
        }
    }

    private static void viewAllMembers() {
        System.out.println("\n=== ALL MEMBERS ===");
        
        if (library.getUsers().isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        
        int index = 1;
        for (Member member : library.getUsers()) {
            System.out.println(index + ". Name: " + member.getName() + 
                             ", Username: " + member.getUsername());
            index++;
        }
    }

    private static void viewAllLoans() {
        System.out.println("\n=== ALL LOANS ===");
        if (library.getLoans().isEmpty()) {
            System.out.println("No active loans.");
            return;
        }

        int index = 1;
        for (Loan loan : library.getLoans()) {
            System.out.println(index + ". User: " + loan.username +
                    ", Book: " + loan.book.title +
                    ", Due Date: " + loan.dueDate);
            index++;
        }
    }

    private static void addMember() {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Member newMember = new Member(name, password, username);
        library.addUser(newMember);
    }

    private static void removeMember() {
        if (!(currentUser instanceof Librarian)) return;
        
        System.out.println("\n=== REMOVE MEMBER ===");
        
        // First show current members
        viewAllMembers();
        
        System.out.print("Enter username of member to remove: ");
        String username = scanner.nextLine();
        
        // Find the member by username
        Member memberToRemove = findMemberByUsername(username);
        
        if (memberToRemove == null) {
            System.out.println("No member found with username: " + username);
            return;
        }
        
        // Check if member has active loans
        boolean hasActiveLoans = false;
        for (Loan loan : library.getLoans()) {
            if (loan.username.equals(username)) {
                hasActiveLoans = true;
                break;
            }
        }
        
        if (hasActiveLoans) {
            System.out.println("Cannot remove member: " + memberToRemove.getUsername() + 
                             " has active loans. Please ensure all books are returned first.");
            return;
        }
        
        // Confirm removal
        System.out.println("Are you sure you want to remove member: " + memberToRemove.getUsername() + 
                          " (Name: " + memberToRemove.getName() + ")? (y/n): ");
        String confirm = scanner.nextLine();
        
        if (confirm.toLowerCase().equals("y") || confirm.toLowerCase().equals("yes")) {
            library.removeUser(memberToRemove);
        } else {
            System.out.println("Member removal cancelled.");
        }
    }

    // ========== UTILITY FUNCTIONS ==========
    private static Book findBookByTitle(String title) {
        for (Book book : library.getBooks()) {
            if (book.title.toLowerCase().contains(title.toLowerCase())) {
                return book;
            }
        }
        return null;
    }
    
    private static Member findMemberByUsername(String username) {
        for (Member member : library.getUsers()) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        return null;
    }
}