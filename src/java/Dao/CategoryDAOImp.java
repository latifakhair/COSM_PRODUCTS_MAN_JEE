/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Category;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImp implements CategoryDAO {

   private Connection getConnection() {
        Connection connection = ConnectionFactory.getConnection();
        if (connection == null) {
            throw new IllegalStateException("Failed to connect to the database");
        }
        return connection;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categorytbl";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Category category = new Category();
                category.setCatCode(resultSet.getInt("CatCode"));
                category.setCatName(resultSet.getString("CatName"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void add(Category category) {
        String query = "INSERT INTO categorytbl (CatCode, CatName) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, category.getCatCode());
            preparedStatement.setString(2, category.getCatName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> searchCategories(String keyword) {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categorytbl WHERE CatName LIKE ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + keyword + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Category category = new Category();
                    category.setCatCode(resultSet.getInt("CatCode"));
                    category.setCatName(resultSet.getString("CatName"));
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    

    @Override
    public void update(Category category) {
        String query = "UPDATE categorytbl SET CatName = ? WHERE CatCode = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, category.getCatName());
            preparedStatement.setInt(2, category.getCatCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int catCode) {
        String query = "DELETE FROM categorytbl WHERE CatCode = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, catCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // You can implement other CRUD methods similarly (getCategoryByName, searchCategories, etc.)
}
