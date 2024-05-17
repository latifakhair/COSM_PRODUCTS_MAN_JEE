<%@page import="Model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/category.css" rel="stylesheet" type="text/css"/>
        <title>Dashboard</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
            function confirmLogout() {
                if (confirm("Are you sure you want to logout?")) {
                    window.location.href = "UserServlet"; // Redirect to the logout servlet
                }
            }
        </script>
        <style>
            .chart-container {
                width: 100%;
                margin: 0 auto;
            }
        </style>
    </head>
    <body>
        <nav data-mdb-navbar-init class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <nav aria-label="breadcrumb">
                    <ul>
                        <li><a href="DashboardServlet">Dashboard</a></li>
                        <li><a href="CategoryServlet">Categories</a></li>
                        <li><a href="ProductServlet">Products</a></li>
                        <li><a class="logout" href="LogoutServlet" onclick="confirmLogout()">Log out</a></li>
                    </ul>
                </nav>
            </div>
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

            <div class="chart-container">
                <canvas id="productChart"></canvas>
            </div>

            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    const productNames = [];
                    const productQuantities = [];
                <%
                        List<Product> products = (List<Product>) request.getAttribute("products");
                        for (Product product : products) {
                %>
                    productNames.push('<%= product.getItName()%>');
                    productQuantities.push(<%= product.getQuantity()%>);
                <%
                        }
                %>

                    const ctx = document.getElementById('productChart').getContext('2d');
                    const productChart = new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: productNames,
                            datasets: [{
                                    label: 'Product Quantities',
                                    data: productQuantities,
                                    backgroundColor: 'rgba(255, 102, 153, 0.7)', // RGBA color
                                    borderColor: 'rgba(255, 102, 153, 1)', // Border color
                                    borderWidth: 1
                                }]
                        },
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        }
                    });
                });
            </script>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
