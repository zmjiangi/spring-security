<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="/login" method="post">
    用户名：<input type="text" name="username"/><br/><br/>
    密码：<input type="password" name="password"/><br/><br/>
    验证码：<input type="text" name="imageCode"/>
    <img src="/api/validateCode" alt="验证码"/><br/><br/>
    <input type="checkbox" name="remember-me" value="true"/>记住我
    <input type="submit" value="登录"/>
</form>
</body>
</html>
