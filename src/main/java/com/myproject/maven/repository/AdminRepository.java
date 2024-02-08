/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.maven.repository;

import com.myproject.maven.model.AdminModel;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */

public class AdminRepository {
    private static Connection koneksi;
    private static PreparedStatement psmt;
    private static Statement stat;
    private static ResultSet rs;
    private static String query;
    AdminModel adminModel = new AdminModel();

    public AdminRepository() {
        try {
            MySQLConnection conn = new MySQLConnection();
            koneksi = conn.getKoneksi();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public <T> boolean checkLogin(T email, T password){
        try {
            query = "SELECT user.name, user.status, role.name_role from role join user on role.id = user.id where user.email=? and user.password=? and user.status = 'active'";
            psmt = koneksi.prepareStatement(query);
            psmt.setString(1, email.toString());
            psmt.setString(2, password.toString());
            rs = psmt.executeQuery();
            if (rs.next()) {
                adminModel.setName(rs.getString("user.name"));
                adminModel.setStatus(rs.getString("user.status"));
                adminModel.setRole(rs.getString("role.name_role"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Gagal Login, Query Error " + e);
            return false;
        }
    }
    public <T> boolean checkEmail(T email){
        try {
            query = "SELECT email FROM user GROUP BY email HAVING COUNT(*) > 1";
            psmt = koneksi.prepareStatement(query);
            psmt.executeQuery();
            psmt.close();
            return true;
        } catch (SQLException e){
            System.out.println("Gagal Pengecekan, Query Error " + e);
            return false;
        }
    }
    public <T> boolean addAdmin(T name, T email, T password){
        try {
            query = "INSERT INTO role (name_role) VALUES ('Admin');";
            psmt = koneksi.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            psmt.executeUpdate();
            rs = psmt.getGeneratedKeys();
            String role_id = "";
            if (rs.next()) {
                role_id += rs.getString(1);
            }
            rs.close();
            psmt.close();
            try {
                query = "INSERT INTO user (name, email, password, status, role_id) VALUES (?, ?, ?, 'active', ?)";
                psmt = koneksi.prepareStatement(query);
                psmt.setString(1, name.toString());
                psmt.setString(2, email.toString());
                psmt.setString(3, password.toString());
                psmt.setString(4, role_id);
                psmt.executeUpdate();
                psmt.close();
                return true;
            } catch (SQLException er){
                System.out.print("Failed to Add in the user table, Query Error " + er);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Failed to Add in the role table, Query Error " + e);
            return false;
        }
    }
    public <T> ArrayList viewAllUser(){
        try {
            query = "SELECT user.id, user.name, user.email, user.password, user.status from user JOIN role " +
                    "ON user.role_id = role.id WHERE role.name_role = 'User'";
            psmt = koneksi.prepareStatement(query);
            rs = psmt.executeQuery();
            ArrayList<String> userDataList = new ArrayList<>();
            while (rs.next()) {
                String userData = rs.getString("user.name") + " | " + rs.getString("user.email") +
                        " | " + rs.getString("user.password") + " | " + rs.getString("user.status");
                userDataList.add(userData);
            }   
            return userDataList;
        } catch (SQLException e){
            System.out.println("Failed to Display, Query Error " + e);
            return null;
        }
    }

    public <T> boolean changeStatusNonActive(T email, T status){
        try {
            query = "UPDATE user SET status = ? WHERE email = ?";
            psmt = koneksi.prepareStatement(query);
            psmt.setString(1, status.toString());
            psmt.setString(2, email.toString());
            psmt.executeUpdate();
            psmt.close();
            return true;
        } catch (SQLException e){
            System.out.println("Failed to Change, Query Error " + e);
            return false;
        }
    }
}

