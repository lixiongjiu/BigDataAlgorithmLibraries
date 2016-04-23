<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户信息</title>
</head>
<body>
  <sf:form modelAttribute="user" method="post">
    请输入原来的密码：<input name="password" type="password"/>
    请输入新的密码：<sf:password path="password"/><sf:errors path="password"/>
    <input type="submit" value="提交"/>
  </sf:form>
</body>
</html>
