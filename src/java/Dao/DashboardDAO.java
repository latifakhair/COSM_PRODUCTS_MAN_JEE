/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Product;
import java.util.List;

/**
 *
 * @author dell
 */

public interface DashboardDAO {
    int getTotalProducts();
    int getTotalCategories();
    public List<Product> getAllProducts();
}