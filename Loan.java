

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
  public LocalDate dueDate;
  public LocalDate borrowedDate;
  public LocalDate returnDate;
  public String username;
  public Book book;



    public Loan (String username , Book b){
        borrowedDate = LocalDate.now();
        dueDate = LocalDate.now().plusDays(7);
        returnDate = LocalDate.now();
        this.username = username;
        // getting all the books info as the book object ( later have to show details of the book )
        this.book = b;

    }

   public void calculateFine(Book b , Library library) {
       returnDate = LocalDate.now();
       if (returnDate.isAfter(dueDate)) {
           int extraDays =(int) ChronoUnit.DAYS.between(dueDate, returnDate);
           System.out.println("Fine for returning late is : $" + extraDays);
       }
       else {
           System.out.println("No extra fine on this book yet.");
       }

   }

}

