package Controller;

import Dao.CategoryDAO;
import Dao.CategoryDAOImp;
import Dao.ProductDAO;
import Dao.ProductDAOImp;
import Model.Category;
import Model.Product;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAOImp();
    private final CategoryDAO categoryDAO = new CategoryDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "search":
                    searchProducts(request, response);
                    break;
                default:
                    break;
            }
        } else {
            listProducts(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addProduct(request, response);
        } else if ("update".equals(action)) {
            updateProduct(request, response);
        } else if ("delete".equals(action)) {
            deleteProduct(request, response);
        } else {
            listProducts(request, response);
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve product details from the form
        String productName = request.getParameter("productName");
        var quantity = Integer.parseInt(request.getParameter("quantity"));
        int price = Integer.parseInt(request.getParameter("price"));
        int category = Integer.parseInt(request.getParameter("category"));

        // Parse the date parameter using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(request.getParameter("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create a Product object with the retrieved details
        Product product = new Product();
        product.setItName(productName);
        product.setQuantity(quantity);
        product.setDate(date);
        product.setPrice(price);
        product.setCategory(category);

        // Call the add method of ProductDAO to add the product to the database
        productDAO.add(product);

        // Redirect back to the product list after adding
        listProducts(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itCode = Integer.parseInt(request.getParameter("itCode"));
        String itName = request.getParameter("itName" + itCode);
        int quantity = Integer.parseInt(request.getParameter("quantity" + itCode));
        int price = Integer.parseInt(request.getParameter("price" + itCode));
        int category = Integer.parseInt(request.getParameter("category" + itCode));
        // Parse the date parameter using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(request.getParameter("date" + itCode));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Product updatedProduct = new Product(itCode, itName, quantity, date, price, category);
        productDAO.update(updatedProduct);

        response.sendRedirect(request.getContextPath() + "/ProductServlet");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the product ID to be deleted from the request parameter
        int productId = Integer.parseInt(request.getParameter("itCode"));
        // Call the delete method of ProductDAO to delete the product from the database
        productDAO.delete(productId);
        // Redirect back to the product list after deletion
        response.sendRedirect(request.getContextPath() + "/ProductServlet");
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch list of products
        List<Product> products = productDAO.getAllProducts();
        // Create a map of category IDs to category names
        Map<Integer, String> categoryMap = new HashMap<>();
        for (Product product : products) {
            int categoryId = product.getCategory();
            String categoryName = productDAO.getCategoryName(categoryId);
            categoryMap.put(categoryId, categoryName);
        }
        // Set attributes for the JSP
        request.setAttribute("products", products);
        request.setAttribute("categoryMap", categoryMap);
        request.getRequestDispatcher("/product.jsp").forward(request, response);
    }

    private void searchProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Product> products = productDAO.searchProducts(keyword);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/product.jsp").forward(request, response);
    }
}
