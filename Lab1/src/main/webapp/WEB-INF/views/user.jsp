<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form đăng nhập</title>
</head>
<body>
    <h1>Đăng nhập User</h1>

    
    <c:if test="${not empty message}">
        <p style="color:red">${message}</p>
    </c:if>

    <form action="create" method="post">
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        <button type="submit">Đăng nhập</button>
    </form>
</body>
</html>