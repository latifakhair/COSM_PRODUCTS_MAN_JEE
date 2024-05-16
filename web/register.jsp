<!DOCTYPE html>
<html lang="en">
    <head>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Registration</title>
    </head>
    <body>
        <form action="UserServlet" method="post">
            <section>
                <div class="signin">
                    <div class="content">
                        <div class="form">
                            <div class="inputBox">
                                <label for="username">Username:</label><br>
                                <input type="text" id="username" name="username"><br>
                            </div>
                            <div class="inputBox">
                                <label for="password">Password:</label><br>
                                <input type="password" id="password" name="password"><br><br>
                            </div>
                            <div class="inputBox">
                                <label for="email">Email:</label><br>
                                <input type="text" id="email" name="email"><br><br>
                            </div>
                            <% String errorMessage = (String) request.getAttribute("errorMessage");
                            if (errorMessage != null) {%>
                            <p><%= errorMessage%></p>
                            <% }%>
                            <div class="inputBox">
                                <input type="submit" value="Register">
                            </div>
                            <div class="register-link">
                                <p>Already have an account? <a href="index.jsp">Login</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <input type="hidden" name="action" value="register">
        </form>
    </body>
</html>
