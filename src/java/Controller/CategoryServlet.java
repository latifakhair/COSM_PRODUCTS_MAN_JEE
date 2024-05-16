package Controller;

import Dao.CategoryDAO;
import Dao.CategoryDAOImp;
import Model.Category;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends HttpServlet {
    
    private final CategoryDAO categoryDAO = new CategoryDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "search":
                    searchCategories(request, response);
                    break;
                default:
                    break;
            }
        } else {
            listCategories(request, response);
        }
    }
    private void addCategory(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String catName = request.getParameter("catName");
    Category category = new Category(catName);
    categoryDAO.add(category);
    listCategories(request, response); // Redirect back to the category list after adding
}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String action = request.getParameter("action");
    if ("add".equals(action)) {
        addCategory(request, response);
    } else if ("update".equals(action)) {
        updateCategory(request, response);
    } else if ("delete".equals(action)) {
        deleteCategory(request, response);
    } else {
        listCategories(request, response);
    }
}

private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    int catCode = Integer.parseInt(request.getParameter("catCode"));
    categoryDAO.delete(catCode);
    listCategories(request, response); // Redirect back to the category list after deletion
}

private void updateCategory(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    int catCode = Integer.parseInt(request.getParameter("catCode"));
    String catName = request.getParameter("catName");
    Category category = new Category();
    category.setCatCode(catCode); // Set the existing catCode
    category.setCatName(catName); // Set the new catName
    categoryDAO.update(category);
    listCategories(request, response); // Redirect back to the category list after updating
}
    private void listCategories(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/category.jsp").forward(request, response);
    }

    private void searchCategories(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Category> categories = categoryDAO.searchCategories(keyword);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/category.jsp").forward(request, response);
    }
}
