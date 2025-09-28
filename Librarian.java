import java.util.ArrayList;

public class Librarian extends Users{




        public Librarian(String name, String password,String username) {

            super(name, password, username);
        }

        //will add a book to the library
        public void addBook(Book b){
            if(b == null ){
                System.out.println("invalid book!");
                return;
            }

            Library library =Library.getInstance();
                library.addBook(b);
                System.out.println(b.title+"added the to library..");

            }


       //removeBook() will remove the book that it has in its parameter from the library
        public void removeBook(Book b){
            if(b == null ){
                System.out.println("invalid book!");
                return;
            }
               Library library =Library.getInstance();
               library.removeBook(b);
               System.out.println(b.title+"removed from library..");

            }


         
        // will display all the books in the arraylist of the library
        public void viewBooks(){
            Library library =Library.getInstance();

            for(Book b: library.getBooks()){
                System.out.println(b.title+" "+b.author+" "+b.ISBN);
            }
        }


    }


