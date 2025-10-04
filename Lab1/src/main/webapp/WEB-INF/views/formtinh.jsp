<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nhập hình chữ nhật</title>
</head>
<body>
<h1>Nhập chiều dài và chiều rộng</h1>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="ketqua" method="post">
    Chiều dài: <input type="text" name="height"><br>
    Chiều rộng: <input type="text" name="width"><br>
    <button type="submit">Tính</button>
</form>
</body>
</html>
