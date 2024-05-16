/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Product;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImp implements ProductDAO {

    // Method to add a new product
    @Override
    public void add(Product product) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            String query = "INSERT INTO itemtbl (ItName, Quantity, Date, Price, category) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, product.getItName());
            stmt.setInt(2, product.getQuantity());
            stmt.setDate(3, new Date(product.getDate().getTime()));
            stmt.setInt(4, product.getPrice());
            stmt.setInt(5, product.getCategory());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing product
    @Override
    public void update(Product product) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            String query = "UPDATE itemtbl SET ItName = ?, Quantity = ?, Date = ?, Price = ?, category = ? WHERE itCode = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, product.getItName());
            stmt.setInt(2, product.getQuantity());
            stmt.setDate(3, new Date(product.getDate().getTime()));
            stmt.setInt(4, product.getPrice());
            stmt.setInt(5, product.getCategory());
            stmt.setInt(6, product.getItCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a product
    @Override
    public void delete(int itCode) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            String query = "DELETE FROM itemtbl WHERE itCode = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, itCode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to search for products by keyword
    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            String query = "SELECT * FROM itemtbl WHERE ItName LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setItCode(rs.getInt("itCode"));
                product.setItName(rs.getString("ItName"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setDate(rs.getDate("Date"));
                product.setPrice(rs.getInt("Price"));
                product.setCategory(rs.getInt("category"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    // Method to retrieve all products
    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            String query = "SELECT * FROM itemtbl";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Product product = new Product();
                product.setItCode(rs.getInt("itCode"));
                product.setItName(rs.getString("ItName"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setDate(rs.getDate("Date"));
                product.setPrice(rs.getInt("Price"));
                product.setCategory(rs.getInt("category"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    // Méthode pour obtenir le nom de catégorie en fonction de l'ID de catégorie
    public String getCategoryName(int categoryId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String categoryName = null;

        try {
            conn = ConnectionFactory.getConnection();
            String query = "SELECT CatName FROM categorytbl WHERE CatCode = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, categoryId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                categoryName = rs.getString("CatName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryName;
    }

    @Override
    public Product getProductById(int itCode) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Product product = null;

        try {
            conn = ConnectionFactory.getConnection();
            String query = "SELECT * FROM itemtbl WHERE itCode = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, itCode);
            rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setItCode(rs.getInt("itCode"));
                product.setItName(rs.getString("ItName"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setDate(rs.getDate("Date"));
                product.setPrice(rs.getInt("Price"));
                product.setCategory(rs.getInt("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return product;
    }

}

