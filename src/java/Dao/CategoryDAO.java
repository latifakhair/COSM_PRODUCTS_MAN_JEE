/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Category;
import java.util.List;

public interface CategoryDAO {
     
    // Method to add a new category
    void add(Category category);
    
    // Method to update an existing category
    void update(Category category);

    // Method to delete a category
    void delete(int catCode);

    // Method to retrieve a category by its code
    
    List<Category> searchCategories(String keyword);

    // Method to retrieve all categories
    List<Category> getAllCategories();
}