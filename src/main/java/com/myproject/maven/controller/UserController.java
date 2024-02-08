package com.myproject.maven.controller;

import com.myproject.maven.service.UserService;
import java.util.regex.*;

public class UserController {
    private UserService userService = new UserService();
    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,100}$";
    final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public <T> boolean checkLogin(T email, T password) {
        Matcher matcher = pattern.matcher(password.toString());
        if (matcher.matches() && userService.checkLogin(email, password)){
            return true;
        } else {
            return false;
        }
    }

    public <T> void addUser(T name, T email, T password){
        Matcher matcher = pattern.matcher(password.toString());
        if (matcher.matches()){
            userService.addUser(name, email, password);
        } else {
            System.out.println("password of at least 8 characters using numbers, uppercase, lowercase and symbols");
        }
    }

    public <T> void addToDo(String ToDo){
        if (ToDo.length() <= 50){
            userService.addToDo(ToDo);
        } else {
            System.out.println("To Do Minimal 50 character");
        }
    }

    public <T> void viewAllToDo(T id){
        userService.viewAllToDo(id);
    }
}
