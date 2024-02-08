/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.maven.model;

/**
 *
 * @author HP
 */
public class AdminModel {
    private static String name, status, role;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        AdminModel.name = name;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        AdminModel.status = status;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        AdminModel.role = role;
    }
}
