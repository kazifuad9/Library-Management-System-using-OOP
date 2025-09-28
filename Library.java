import java.util.ArrayList;

public class Library {
    // Static instance - only one library will exist
    private static Library instance = null;
    
    // Static collections
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Member> users = new ArrayList<>();
    private static ArrayList<Loan> loans = new ArrayList<>();

    // Private constructor - prevents external instantiation
    private Library() {
        // Constructor is private so that only 1 library can exist
    }

    // Static method to get the single instance or access to the library
    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }


    public void addUser(Member u){
        if( u == null){
            System.out.println("User is not valid so cannot be added!");
            return;
        }
        users.add(u);
        System.out.println("User added successfully.");
    }

    public void removeUser(Member u){
        if( u == null){
            System.out.println("User is not valid so cannot be removed!");
            return;
        }
        users.remove(u);
        System.out.println("User removed successfully.");
    }

    public void searchBook(Book b){
        if(b == null){
            System.out.println("Book not found!");
            return;
        }
        for(Book b1:books){
            if(b1.title.equals(b.title)){
                System.out.println("Book found!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void addLoan(Loan l){
        if(l == null){
            System.out.println("Loan is not valid so cannot be added!");
            return;
        }
        loans.add(l);
        System.out.println("Loan added successfully!");
    }

    public void removeLoan(Loan l){
        if(l == null){
            System.out.println("Loan is not valid so cannot be removed!");
            return;
        }
        boolean removed = loans.remove(l);
        if (removed) {
            System.out.println("Loan removed successfully!");
        } else {
            System.out.println("Loan not found in this library!");
        }
    }

    public ArrayList<Loan> getLoans(){
        return loans;
    }

    public void addBook(Book b){
        if(b == null){
            System.out.println("Book is not valid so cannot be added!");
            return;
        }
        books.add(b);
    }

    public void removeBook(Book b){
        if(b == null){
            System.out.println("Book is not valid so cannot be removed!");
            return;
        }
        books.remove(b);
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    public ArrayList<Member> getUsers(){
        return users;
    }
}
