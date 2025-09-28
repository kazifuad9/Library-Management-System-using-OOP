import java.util.Scanner;
public  abstract class Users {
    Scanner sc = new Scanner(System.in);
    public String name;
    private String password;
    private String username;

    public Users(String name, String password, String username) {
        this.name = name;
        this.password = password;
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        System.out.println("Enter password to set username");
        String pass = sc.nextLine();
        if(pass.equals(password)){
            this.username = username;
            System.out.println("Username set successfully!");
        }
        else{
            System.out.println("Wrong password!Couldn't set username!");
        }

    }
    public String getPassword() {
        return password;
    }
    public void setPassword() {
        System.out.println("Enter current password:");
        String pass = sc.nextLine();
        if(pass.equals(password)){
            System.out.println("Enter new password:");
            String New_password = sc.nextLine();
            this.password = New_password;
            System.out.println("Password set successfully!");
        }

    }

    public void logIn(){
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();
        if(username.equals(this.username) && password.equals(this.password)){
            System.out.println("Logged in successfully!");
        }
        else{
            System.out.println("Wrong username or password!");
        }
    }
    public void logOut(){
        System.out.println("Logged out successfully!");
    }



}
