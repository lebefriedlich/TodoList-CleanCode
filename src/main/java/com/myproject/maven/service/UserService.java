package com.myproject.maven.service;

import com.myproject.maven.model.UserModel;
import com.myproject.maven.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import com.myproject.maven.model.toDo;

public class UserService{
    UserRepository userRepository = new UserRepository();
    UserModel userModel = new UserModel();
    public <T> boolean checkLogin(T email, T password){
        if (userRepository.checkLogin(email, password)) {
            if (userModel.getRole().equals("User") && userModel.getStatus().equals("Active")){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public <T> void addUser (T name, T email, T password){
        if (userRepository.checkEmail(email)) {
            if (userRepository.addUser(name, email, password)) {
                System.out.println("You have successfully added a new user");
            }
        } else {
            System.out.println("The email has arrived");
        }
    }

    public void addToDo (String ToDo){
        if (userRepository.addToDo(ToDo)){
            System.out.println("You have successfully added your To Do List");
        } else {
            System.out.println("You failed to add To Do List");
        }
    }

     public <T> void viewAllToDo(T id){
        ArrayList<toDo> toDoList = userRepository.viewAllToDo(id);
        if (toDoList != null){
            System.out.println("Total To Do: " + getTotal(toDoList) + "\n");
            for (int i = 0; i < toDoList.size(); i++) {
                System.out.println(i+1 + ". " + toDoList.get(i).getToDo());
            }
            System.out.println("\nYou Successfully Show To Do List");
        } else {
            System.out.println("You failed to View To Do List");
        }
     }

     public <T> int getTotal(Collection<T> datas){
        return datas.size();
     }
}
