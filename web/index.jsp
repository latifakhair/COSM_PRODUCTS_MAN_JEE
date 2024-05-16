<!DOCTYPE html>
<html lang="en">
    <head>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Login</title>
    </head>
    <body>
        <section class="vh-100">
            <div class="container py-5 h-100">
                <div class="row d-flex align-items-center justify-content-center h-100">
                    <div class="col-md-8 col-lg-7 col-xl-6">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
                             class="img-fluid" alt="Phone image">
                    </div>
                    <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">

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
                                            <% String errorMessage = (String) request.getAttribute("errorMessage");
                                if (errorMessage != null) {%>
                                            <p><%= errorMessage%></p>
                                            <% }%>
                                            <div class="inputBox">
                                                <input type="submit" value="Login">
                                            </div>
                                            <div class="register-link">
                                                <p>Don't have an account? <a href="register.jsp">Register</a></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
