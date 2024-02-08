/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.maven.controller;

import com.myproject.maven.service.AdminService;
import java.util.regex.*;

/**
 *
 * @author HP
 */
public class AdminController {

    private AdminService adminService = new AdminService();
    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,100}$";
    final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public <T> boolean checkLogin(T email, T password) {
        Matcher matcher = pattern.matcher(password.toString());
        if (matcher.matches() && adminService.checkLogin(email, password)){
            return true;
        } else {
            return false;
        }
    }

    public <T> void addAdmin(T name, T email, T password){
        Matcher matcher = pattern.matcher(password.toString());
        if (matcher.matches()){
            adminService.addAdmin(name, email, password);
        } else {
            System.out.println("password of at least 8 characters using numbers, uppercase, lowercase and symbols");
        }
    }

    public void viewUser(){
        adminService.viewAllUser();
    }

    public <T> void changeStatus(T email, T status){
        adminService.changeStatus(email, status);
    }
}
