<%@page import="Model.Product"%>

<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="css/category.css" rel="stylesheet" type="text/css"/>
        <title>Dashboard</title>
        <!-- Mettez ici vos liens CSS, scripts JS, etc. -->
        <script>
            function confirmLogout() {
                if (confirm("Are you sure you want to logout?")) {
                    window.location.href = "UserServlet"; // Redirect to the logout servlet
                }
            }
        </script>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="DashboardServlet">Dashbard</a></li>
                <li><a href="CategoryServlet">Categories</a></li>
                <li><a href="ProductServlet">Products</a></li>
                <li><a class="logout" href="LogoutServlet" onclick="confirmLogout()">Log out</a></li>
            </ul>
        </nav>

        <div>

            <div class="panel-container">
                <div class="panel">
                    <div class="panel-header">
                        <h2>Total des produits</h2>
                    </div>
                    <div class="panel-body">
                        <%= request.getAttribute("totalProducts")%>
                    </div>
                </div>

                <div class="panel">
                    <div class="panel-header">
                        <h2>Total des catégories</h2>
                    </div>
                    <div class="panel-body">
                        <%= request.getAttribute("totalCategories")%>
                    </div>
                </div>
            </div>


            <div class="chart">
                <%
                    List<Product> products = (List<Product>) request.getAttribute("products");
                    for (Product product : products) {
                %>
                <div class="bar-container">
                    <div class="bar" style="height: <%= product.getQuantity()%>px;"></div>
                    <div class="label"><%= product.getItName()%></div>
                    <div class="quantity-label"><%= product.getQuantity()%></div>
                </div>
                <% }%>
            </div>

            <!-- Mettez ici d'autres éléments HTML pour afficher les statistiques de votre tableau de bord -->
    </body>
</html>
