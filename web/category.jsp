<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/category.css" rel="stylesheet" type="text/css"/>
                 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

        <title>Category</title>
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
                    <h5> </h5>

                    <form action="CategoryServlet" method="post">
                        <input type="hidden" name="action" value="add">
                        <label for="catName">AJOUTER Name:</label>
                        <input type="text" name="catName" id="catName" required>
                        <input class="submit" type="submit" value="Add Category">
                    </form>

                </div>
                <div class="form-column">
                    <h5></h5>
                    <form action="CategoryServlet" method="get">
                        <input type="hidden" name="action" value="search">
                        <label for="catName">CHERCHER :</label>
                        <input type="text" name="keyword" placeholder="Enter keyword">
                        <input type="submit" value="Search">
                    </form>
                </div>
            </div>

            <div>
                <tr></tr>
                <table border="1" class="table table-striped">
                    <thead class="table table-bordered table-dark">
                        <tr>
                            <th  scope="col">Code de la catégorie</th>
                            <th  scope="col">Nom de la catégorie</th>
                            <th  scope="col">Action</th> <!-- Ajouter une nouvelle colonne pour les actions -->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="category" items="${categories}">
                            <tr>
                                <td >${category.catCode}</td>
                                <td>${category.catName}</td>
                                <!-- Update button -->
                                <td>
                                    <form action="CategoryServlet" method="post">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="catCode" value="${category.catCode}">
                                        <input type="text" name="catName" value="${category.catName}" required>
                                        <input type="submit" value="Update Category">
                                    </form>

                                    <!-- Delete button -->

                                    <form action="CategoryServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this category?');">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="catCode" value="${category.catCode}">
                                        <input type="submit" value="Delete">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
