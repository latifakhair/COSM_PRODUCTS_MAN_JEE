<!-- Add a message div to display success messages -->
<div id="message" style="display:none;"></div>

<!-- Add JavaScript function to show messages -->
<script>
    function showMessage(message) {
        var messageDiv = document.getElementById("message");
        messageDiv.innerHTML = message;
        messageDiv.style.display = "block";
        setTimeout(function () {
            messageDiv.style.display = "none";
        }, 3000); // Hide the message after 3 seconds
    }
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/category.css" rel="stylesheet" type="text/css"/>
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
        <nav>
            <ul>
                <li><a href="DashboardServlet">Dashbard</a></li>
                <li><a href="CategoryServlet">Categories</a></li>
                <li><a href="ProductServlet">Products</a></li>
                <li><a class="logout" href="LogoutServlet" onclick="confirmLogout()">Log out</a></li>
            </ul>
        </nav>
        <div class="container">
            <!-- Add a message div to display success messages -->
            <div id="message" style="display:none;"></div>
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
                <table border="1" class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Code de la catégorie</th>
                            <th>Nom de la catégorie</th>
                            <th>Action</th> <!-- Ajouter une nouvelle colonne pour les actions -->
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
            <%-- JSP code to check if success attribute is set and display success message --%>
            <%
                String successMessage = (String) request.getAttribute("success");
                if (successMessage != null) {
            %>
            <script>
                alert("<%= successMessage%>");
            </script>
            <%
                }
            %>
    </body>
</html>
