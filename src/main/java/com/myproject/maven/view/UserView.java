package com.myproject.maven.view;

import com.myproject.maven.controller.UserController;
import com.myproject.maven.model.UserModel;

import java.util.Scanner;

public class UserView {
    UserModel userModel = new UserModel();
    UserController userController = new UserController();
    Scanner scanner = new Scanner(System.in);


    public void showMenuUser() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\nUser Menu:");
            System.out.println("Hello, " + userModel.getName() + "\t\tStatus: " + userModel.getStatus());
            System.out.println("1. Add To Do");
            System.out.println("2. View All To Do");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Input To Do");
                    String toDo = scanner.nextLine();
                    userController.addToDo(toDo);
                    break;
                case 2:
                    userController.viewAllToDo(userModel.getId());
                    break;
                case 3:
                    loggedIn = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
