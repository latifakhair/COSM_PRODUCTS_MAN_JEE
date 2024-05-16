<%-- 
    Document   : product
    Created on : 14 mai 2024, 16:50:39
    Author     : utilisateur
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/category.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

        <title>Product Management</title>
        <script>
            function confirmLogout() {
                if (confirm("Are you sure you want to logout?")) {
                    window.location.href = "UserServlet"; // Redirect to the logout servlet
                }
            }
        </script>
    </head>
    <body>
        <nav data-mdb-navbar-init class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <nav aria-label="breadcrumb">
                    <ul>
                        <li><a href="DashboardServlet">Dashbard</a></li>
                        <li><a href="CategoryServlet">Categories</a></li>
                        <li><a href="ProductServlet">Products</a></li>
                        <li><a class="logout" href="LogoutServlet" onclick="confirmLogout()">Log out</a></li>
                    </ul>
                </nav>
            </div>

        </nav>
        <div class="container">
            <div class="form-row">
                <div class="form-column">
                    <h3 class="title" style="font-style: italic;">Add New Product</h3>
                    <form action="ProductServlet" method="post"  class="custom-form">
                        <input type="hidden" name="action" value="add">
                        <label for="productName">Product Name:</label>
                        <input type="text" name="productName" id="productName" required>
                        <label for="quantity">Quantity:</label>
                        <input type="number" name="quantity" id="quantity" required>
                        <label for="date">Date:</label>
                        <input type="date" name="date" id="date" required>
                        <label for="price">Price:</label>
                        <input type="number" name="price" id="price" required>
                        <label for="category">Category:</label>
                        <select name="category" id="category">
                            <!-- Populate options with categories -->
                            <c:forEach var="category" items="${categoryMap}">
                                <option value="${category.key}">${category.value}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Add Product">
                    </form>
                </div>
            </div>
            <div class="form-column">
                <h3 class="title" style="font-style: italic;">Search Products</h3>
                <form action="ProductServlet" method="get">
                    <input type="hidden" name="action" value="search">
                    <label for="keyword">Keyword:</label>
                    <input type="text" name="keyword" id="keyword" placeholder="Enter keyword">
                    <input type="submit" value="Search">
                </form>
            </div>
            <div>
                <table border="1" border="1" class="table table-striped">
                    <thead class="table table-bordered table-dark">
                        <tr>
                            <th>Product Code</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Date</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Action</th> <!-- Add a new column for actions -->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.itCode}</td>
                                <td>${product.itName}</td>
                                <td>${product.quantity}</td>
                                <td>${product.date}</td>
                                <td>${product.price}</td>
                                <td>${categoryMap[product.category]}</td> <!-- Utiliser la map pour afficher le nom de la catégorie -->
                                <td class="action-column"> <!-- Add a class to style the action column -->
                                    <form action="ProductServlet" method="post" class="custom-form">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="itCode" value="${product.itCode}">
                                        <div class="form-group">
                                            <label for="itName${product.itCode}">Product Name:</label>
                                            <input type="text" name="itName${product.itCode}" value="${product.itName}" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="quantity${product.itCode}">Quantity:</label>
                                            <input type="number" name="quantity${product.itCode}" value="${product.quantity}" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="date${product.itCode}">Date:</label>
                                            <input type="date" name="date${product.itCode}" value="${product.date}" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="price${product.itCode}">Price:</label>
                                            <input type="number" name="price${product.itCode}" value="${product.price}" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="category${product.itCode}">Category:</label>
                                            <select name="category${product.itCode}" id="category${product.itCode}">
                                                <!-- Populate options with categories -->
                                                <c:forEach var="category" items="${categoryMap}">
                                                    <option value="${category.key}" ${category.key == product.category ? 'selected' : ''}>${category.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" value="Update ">
                                        </div>
                                    </form>
                                    <!-- Delete button -->
                                    <form action="ProductServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this product?');">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="itCode" value="${product.itCode}">
                                        <input type="submit" value="Delete">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>

