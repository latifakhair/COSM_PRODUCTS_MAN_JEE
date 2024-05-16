<!DOCTYPE html>
<html lang="en">
<head>
    <link href="css/main.css" rel="stylesheet" type="text/css"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login</title>
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
                   <% String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) { %>
        <p><%= errorMessage %></p>
    <% } %>
    <div class="inputBox">
        <input type="submit" value="Login">
    </div>
</div>
            </div>
        </div>
    </section>
</form>
</body>
</html>
