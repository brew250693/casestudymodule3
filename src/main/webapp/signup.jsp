<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/4/2021
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post">
    <h1>Sign up</h1>
    <div class="row">
        <div class="col-md-6">
            <label>Họ và tên</label>
            <input name="nameRes" type="text" placeholder="Họ và tên">
        </div>
        <div class="col-md-6">
            <label>Ngày sinh</label>
            <input name="birthRes" type="text" placeholder="Ngày sinh">
        </div>
        <div class="col-md-6">
            <label>E-mail</label>
            <input name="emailRes" type="text" placeholder="E-mail">
        </div>
        <div class="col-md-6">
            <label>Số điện thoại</label>
            <input name="phoneRes" type="text" placeholder="Số điện thoại">
        </div>
        <div class="col-md-6">
            <label>Mật khẩu</label>
            <input name="passwordRes" type="password" placeholder="Mật khẩu">
        </div>
        <div class="col-md-6">
            <label>Nhập lại mật khẩu</label>
            <input name="retypePass" type="password" placeholder="Nhập lại mật khẩu">
        </div>
        <div class="col-md-12">
                <input class="btn" type="submit" value="Đăng ký"/>
        </div>
    </div>
    <a href="/users?action=login">login</a>
</form>
</body>
</html>
