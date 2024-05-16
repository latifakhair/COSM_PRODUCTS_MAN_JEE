/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Product;
import java.util.List;

public interface ProductDAO {

    // Method to add a new product
    void add(Product product);

    // Method to update an existing product
    void update(Product product);

    // Method to delete a product
    void delete(int itCode);

    // Method to search for products by keyword
    List<Product> searchProducts(String keyword);

    // Method to retrieve all products
    List<Product> getAllProducts();

    public String getCategoryName(int categoryId);

    public Product getProductById(int itCode);

}
