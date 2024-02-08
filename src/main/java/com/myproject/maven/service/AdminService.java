/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.maven.service;

import com.myproject.maven.model.AdminModel;
import com.myproject.maven.model.toDo;
import com.myproject.maven.repository.AdminRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author HP
 */
public class AdminService {

    AdminRepository adminRepository = new AdminRepository();
    AdminModel adminModel = new AdminModel();
    public <T> boolean checkLogin(T email, T password){
        if (adminRepository.checkLogin(email, password)) {
            if (adminModel.getRole().equals("Admin") && adminModel.getStatus().equals("Active")){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public <T> void addAdmin (T name, T email, T password){
        if (adminRepository.addAdmin(name, email, password) && adminRepository.checkEmail(email)) {
            System.out.println("You have successfully added a new Admin");
        } else {
            System.out.println("The email has arrived ");
        }
    }
    public <T> int getTotal(Collection<T> datas){
        return datas.size();
    }
    public <T> void viewAllUser (){
        ArrayList<toDo> UserList = adminRepository.viewAllUser();
        if (UserList != null){
            System.out.println("Total User: " + getTotal(UserList) + "\n");
            for (int i = 0; i < UserList.size(); i++) {
                System.out.println(i+1 + ". " + UserList.get(i));
            }
            System.out.println("\nYou Successfully Show To Do List");
        } else {
            System.out.println("You failed to View To Do List");
        }
    }

    public <T> void changeStatus(T email, T status){
        if (adminRepository.changeStatusNonActive(email, status)){
            System.out.println("Successfully changed user status");
        } else {
            System.out.println("Unsuccessful changing user status");
        }
    }

}

