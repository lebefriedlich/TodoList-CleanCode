/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.maven.view;

import com.myproject.maven.controller.AdminController;
import com.myproject.maven.model.AdminModel;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class AdminView {

    AdminModel adminModel = new AdminModel();
    AdminController adminController = new AdminController();
    Scanner scanner = new Scanner(System.in);
    public void showMenuAdmin() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\nAdmin Menu:");
            System.out.println("Halo, " + adminModel.getName() + "\t\tStatus: " + adminModel.getStatus());
            System.out.println("1. Add Admin");
            System.out.println("2. View All Users");
            System.out.println("3. Change User Status");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("\nName: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    adminController.addAdmin(name, email, password);
                    break;
                case 2:
                    adminController.viewUser();
                    break;
                case 3:
                    System.out.print("Email: ");
                    String email1 = scanner.nextLine();
                    System.out.println("1. Active");
                    System.out.println("2. Non Active");
                    int choiceStatus = Integer.parseInt(scanner.nextLine());
                    String status = null;
                    if (choiceStatus == 1){
                        status = "Active";
                    } else if (choiceStatus == 2) {
                        status = "Non Active";
                    }
                    adminController.changeStatus(email1, status);
                    break;
                case 4:
                    loggedIn = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
