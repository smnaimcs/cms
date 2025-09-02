package com.example.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTest {
    public static void main(String[] args) {
        // Change these according to your setup
        String jdbcURL = "jdbc:mysql://localhost:3306/your_db_name";
        String dbUser = "webapp_user";
        String dbPassword = "My$trongP@ss123";

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Try connection
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            if (conn != null) {
                System.out.println("✅ Database connected successfully!");
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed.");
            e.printStackTrace();
        }
    }
}
