<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/5/2021
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <h1>Login</h1>
    <div class="col-lg-6">
        <div class="login-form">
            <div class="row">
                <div class="col-md-6">
                    <label>E-mail</label>
                    <input class="form-control" name="emailLog" type="text" placeholder="E-mail">
                </div>
                <div class="col-md-6">
                    <label>Mật khẩu</label>
                    <input class="form-control" name="passwordLog" type="text" placeholder="Mật khẩu">
                </div>
                <div class="col-md-12">
                    <input class="btn" type="submit" value="Đăng nhập">
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
