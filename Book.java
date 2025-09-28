public class Book {
    public String title;
    public String author;
    public String ISBN;
    private boolean isBorrowed;
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        isBorrowed = false;
    }
    public void bookDetails(){
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
        if (isBorrowed == true) {
            System.out.println("Book is not available for borrowing");
        }
        else  {
            System.out.println("Book is available for borrowing");
        }
    }
    public void setBorrowed() {
        isBorrowed = true;
    }
    public void setReturned() {
        isBorrowed = false;
    }
    public boolean isBorrowed() {
        return isBorrowed;
    }

}
