<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/head.jsp"/>
<head>
    <title>登录提示</title>
    <%--<link rel="stylesheet" href="/resources/css/bootstrap.min.css">--%>
    <%--<script type="text/javascript" src="/resources/js/jquery-1.12.2.js"></script>--%>
    <%--<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>--%>
</head>
<body>


<div class="banner-middle">
    <!-- technology -->
    <div class="technology">
        <h3>抱歉 !</h3>

        <p>您尚未登录，无法使用某些功能</p>
        <br/>

        <h3 class="text-center"><i>权限不足</i></h3>

        <div class="back-to-home">
            <a href="/user/login">您是去登录<span class="glyphicon glyphicon-log-in"></span> </a><br/>
            <a href="/user/register"> 还是去注册<span class="glyphicon glyphicon-registration-mark"></span> </a><br/>
            <a href="/user/home">亦或是回到主页<span class="glyphicon glyphicon-home"></span> </a>
        </div>
    </div>
    <!-- //technology -->
</div>

<jsp:include page="../common/foot.jsp"/>
</body>
</html>
