package userManagement;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String username;
    private String password;
    private String name;
    private String mobileNumber;
    private String address;
    private String role; // Either Librarian or Patron.

    private static List<Account> users = new ArrayList<>();

    public Account(String username, String password, String name, String mobileNumber, String address, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean verifyCredentials(String username, String password, String role) {
        return this.username.equals(username) && this.password.equals(password) && this.role.equals(role);
    }

    public static void addUser(Account account) {
        users.add(account);
    }

    public static Account findUser(String username) {
        for (Account user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static List<Account> getUsers() {
        return users;
    }

    public static void signup(String username, String password, String name, String mobileNumber, String address, String role) {
        if (findUser(username) != null) {
            System.out.println("User already exists. Please login.");
            return;
        }
        Account newUser = new Account(username, password, name, mobileNumber, address, role);
        addUser(newUser);
        System.out.println(role + " registered successfully!");
    }

    public static void login(String username, String password, String role) {
        Account user = findUser(username);
        if (user != null && user.verifyCredentials(username, password, role)) {
            if (role.equals("Librarian")) {
                Librarian librarian = new Librarian(user.username, user.name, user.mobileNumber, user.address, user.password);
                librarian.performLibrarianActions();
            } else if (role.equals("Patron")) {
                Patron patron = new Patron(user.username, user.name, user.mobileNumber, user.address, user.password);
                patron.performPatronActions();
            }
        } else {
            System.out.println("Invalid credentials or role. Please try again.");
        }
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }


}
