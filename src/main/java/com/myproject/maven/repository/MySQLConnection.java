package com.myproject.maven.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private Connection connect;
    private String driverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost/todolist";
    String user = "root";
    String pass = "";

    public Connection getKoneksi() throws SQLException {
        if (connect == null) {
            try {
                Class.forName(driverName);
                System.out.println("Class Driver Ditemukan");
                try {
                    connect = DriverManager.getConnection(url, user, pass);
                    System.out.println("Koneksi Database Sukses");
                } catch (SQLException se) {
                    System.out.println("Koneksi Database Gagal: " + se);
                    System.exit(0);
                }
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Class Driver Tidak Ditemukan, Terjadi Kesalahan Pada: " + cnfe);
                System.exit(0);
            }
        }
        return connect;
    }
}
