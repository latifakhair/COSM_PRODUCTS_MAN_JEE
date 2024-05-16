package Dao;

import Model.Category;
import Model.Product;
import util.ConnectionFactory;
import util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAOImp implements DashboardDAO {
    private final Connection connection;

    public DashboardDAOImp() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public int getTotalProducts() {
        int totalProducts = 0;
        try {
            String sql = "SELECT COUNT(*) FROM itemtbl";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    totalProducts = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalProducts;
    }

    @Override
    public int getTotalCategories() {
        int totalCategories = 0;
        try {
            String sql = "SELECT COUNT(*) FROM categorytbl";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    totalCategories = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCategories;
    }
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
}
