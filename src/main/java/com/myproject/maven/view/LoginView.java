package com.myproject.maven.view;

import java.util.Scanner;
import com.myproject.maven.controller.AdminController;
import com.myproject.maven.controller.UserController;
import com.myproject.maven.model.AdminModel;
import com.myproject.maven.model.UserModel;

public class LoginView {
    AdminView adminView = new AdminView();
    UserView userView = new UserView(   );
    AdminController adminController = new AdminController();
    UserController userController = new UserController();
    UserModel userModel = new UserModel();
    AdminModel adminModel = new AdminModel();
    Scanner scanner = new Scanner(System.in);

    public void firstMenu(){
        boolean flag = true;
        while (flag) {
            System.out.println("1. Log In");
            System.out.println("2. Sign Up");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    showLogin();
                    flag = false;
                    break;
                case 2:
                    showSignUp();
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void showSignUp(){
        System.out.print("Have an account? (y/n) ");
        String choice = scanner.nextLine();
        switch (choice){
            case "y":
                showLogin();
                break;
            case "n":
                System.out.println("\n\t\t----- SIGN UP -----");
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                userController.addUser(name, email, password);
                System.out.println("You will switch to the Log In menu");
                showLogin();
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    public void showLogin() {
        System.out.print("Don't have an account? (y/n) ");
        String choice = scanner.nextLine();
        switch (choice){
            case "y":
                showSignUp();
                break;
            case "n":
                boolean logIn = true;
                while (logIn) {
                    System.out.println("\t\t----- LOG IN -----");
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    if (userController.checkLogin(email, password) || adminController.checkLogin(email, password)){
                        if (userModel.getRole().equals("User")){
                            userView.showMenuUser();
                            logIn = false;
                        } else if (adminModel.getRole().equals("Admin")){
                            adminView.showMenuAdmin();
                            logIn = false;
                            break;
                        } else {
                            System.out.println("Login failed. Invalid credentials.");
                        }
                    } else {
                        System.out.println("Login failed. Invalid email or password.\n");
                    }
                }
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.firstMenu();
    }
}
