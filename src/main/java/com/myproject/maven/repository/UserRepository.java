package com.myproject.maven.repository;

import com.myproject.maven.model.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import com.myproject.maven.model.toDo;

public class UserRepository {
    private static Connection koneksi;
    private static PreparedStatement psmt;
    private static Statement stat;
    private static ResultSet rs;
    private static String query;
    UserModel userModel = new UserModel();

    public UserRepository() {
        try {
            MySQLConnection conn = new MySQLConnection();
            koneksi = conn.getKoneksi();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public <T> boolean checkLogin(T email, T password){
        try {
            query = "SELECT user.name, user.status, role.name_role, user.id from role join user on role.id = user.id where user.email=? and user.password=? and user.status = 'active'";
            psmt = koneksi.prepareStatement(query);
            psmt.setString(1, email.toString());
            psmt.setString(2, password.toString());
            rs = psmt.executeQuery();
            if (rs.next()) {
                userModel.setName(rs.getString("user.name"));
                userModel.setStatus(rs.getString("user.status"));
                userModel.setRole(rs.getString("role.name_role"));
                userModel.setId(rs.getString("user.id"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Login Failed, Query Error " + e);
            return false;
        }
    }

    public <T> boolean checkEmail(T email){
        try {
            query = "SELECT email FROM user GROUP BY email HAVING COUNT(*) > 1";
            psmt = koneksi.prepareStatement(query);
            rs = psmt.executeQuery();
            if (rs.next()){
                rs.close();
                psmt.close();
                return false;
            } else {
                rs.close();
                psmt.close();
                return false;
            }
        } catch (SQLException e){
            System.out.println("Check Failed, Query Error " + e);
            return false;
        }
    }

    public <T> boolean addUser(T name, T email, T password){
        try {
            query = "INSERT INTO role (name_role) VALUES ('User');";
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
                query = "INSERT INTO user (name, email, password, status, role_id) VALUES (?, ?, ?, 'Active', ?)";
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

    public boolean addToDo (String ToDo){
        try{
            query = "INSERT INTO todo(todo, user_id) VALUES (?, ?)";
            psmt = koneksi.prepareStatement(query);
            psmt.setString(1, ToDo);
            psmt.setString(2, userModel.getId());
            psmt.executeUpdate();
            psmt.close();
            return true;
        } catch (SQLException e){
            System.out.println("Failed to Add To Do in the todo table, Query Error " + e);
            return false;
        }
    }

    public <T> ArrayList viewAllToDo(T id){
        try {
            query = "SELECT id, todo, user_id FROM todo WHERE user_id = ?";
            psmt = koneksi.prepareStatement(query);
            psmt.setString(1, id.toString());
            ArrayList<toDo> toDoList = new ArrayList<>();
            rs = psmt.executeQuery();
            while (rs.next()){
                int toDoId = rs.getInt("id");
                String Todo = rs.getString("todo");
                int user_id = rs.getInt("user_id");
                toDo data = new toDo(toDoId, user_id, Todo);
                toDoList.add(data);
            }

            psmt.close();
            return toDoList;
        } catch (SQLException e){
            System.out.println("Check Failed, Query Error " + e);
            return null;
        }
    }
}
