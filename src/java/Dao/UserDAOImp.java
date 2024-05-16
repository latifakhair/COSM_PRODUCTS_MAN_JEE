/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.User;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImp implements UserDAO {
    
   private Connection getConnection() {
        Connection connection = ConnectionFactory.getConnection();
        if (connection == null) {
            throw new IllegalStateException("Failed to connect to the database");
        }
        return connection;
    }
    
    @Override
    public boolean validateUser(User user) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if user exists, false otherwise
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
