import java.util.ArrayList;

public class Member extends Users{

    private   ArrayList<Book> borrowedBooks = new ArrayList<>();


    public Member(String name, String password, String username) {
        super(name, password, username);
    }

    public void borrowBook(Book b ) {
        // getting the library instance to add the loan
        Library library =Library.getInstance();

        // checking if the book is right or not
        if(b== null){
            System.out.println("Book not found!");
            return;
        }


        // checking if the book is already borrowed or not
        if (b.isBorrowed()){
            System.out.println("Book is already borrowed!");
            return;
        }
        // now set the book as borrowed and add it to the borrowedBooks arraylist
        b.setBorrowed();
        borrowedBooks.add(b);

        // now add the book as loaned and add the loan to the library
        Loan l = new Loan(getUsername(),b );
        library.addLoan(l);
        System.out.println("Book borrowed successfully!");

    }
    public void checkFine(Book b ) {
        // getting the library access
        Library library =Library.getInstance();

        // checking if the book is right or not
        if(b== null){
            System.out.println("Book not found in the library!");
            return;
        }

        // verify whether this member borrowed this book or not
        if (!borrowedBooks.contains(b)) {
            System.out.println("No active loan for this book.");
            return;
        }
        // have to find the loan from library and then have to calculate the fine from that loan
        ArrayList<Loan> all_loans = library.getLoans();
        Loan desired_loan = null ;
        for (Loan found : all_loans) {
            if(found.username.equals(getUsername()) && b == found.book){
                desired_loan = found;
                break;
            }
        }
        
        // Check if loan was found before using it
        if (desired_loan == null) {
            System.out.println("No loan record found for this book.");
            return;
        }
        desired_loan.calculateFine(b, library);
    }
    
    public void returnBook(Book b ) {
        Library library =Library.getInstance();

        // checking if the book is right or not
        if(b== null){
            System.out.println("Book not found in the library!");
            return;
        }

        // verify whether this member borrowed this book or not
        if (!borrowedBooks.contains(b)) {
            System.out.println("You haven't borrowed this book.");
            return;
        }

        // Find and remove the loan when actually returning the book
        ArrayList<Loan> all_loans = library.getLoans();
        Loan desired_loan = null ;
        for (Loan found : all_loans) {
            if(found.username.equals(getUsername()) && b == found.book){
                desired_loan = found;
                break;
            }
        }
        
        if (desired_loan != null) {
            // Calculate final fine before removing loan
            desired_loan.calculateFine(b, library);
            // Remove the loan from library
            library.removeLoan(desired_loan);
        }
        
        // Remove from borrowed books and mark as returned
        borrowedBooks.remove(b);
        b.setReturned();
        
        System.out.println("Book returned successfully!");
    }
    
    public void borrowedBooksDetails() {
            System.out.println("Your borrowed books:");
            if (borrowedBooks.isEmpty()) {
                System.out.println("No books currently borrowed.");
                return;
            }
            
            for(Book b : borrowedBooks){
                System.out.println("Book: " + b.title);
                checkFine(b);
                System.out.println("---");
            }
    }
}
