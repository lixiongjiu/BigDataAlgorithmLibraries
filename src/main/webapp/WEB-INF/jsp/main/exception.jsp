<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>异常显示</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script type="text/javascript" src="/resources/js/jquery-1.12.2.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="nav navbar-inverse" role="navigation">
    <%--导航栏标题--%>
    <div class="navbar-header">

        <button class="btn-default navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="/user/home" class="navbar-brand"><span class="glyphicon glyphicon-home">主页</span></a>
    </div>


    <div class="collapse navbar-collapse ">
        <div class="navbar-text">

            <c:choose>
                <c:when test="${userName!=null}">
                    <strong>欢迎您：${userName}&nbsp;</strong>

                    <a href="/io/upload" class="navbar-link">
                        <span class="glyphicon glyphicon-upload"></span>上传算法包
                    </a>


                    <a href="/io/download" class="navbar-link">
                        <span class="glyphicon glyphicon-download"></span>下载结果
                    </a>

                </c:when>
                <c:otherwise>
                    <strong>欢迎您：游客</strong>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="navbar-form navbar-right">
            <c:choose>
                <c:when test="${userName!=null}">

                    <a href="/user/${userName}" class="navbar-link">
                        <span class="glyphicon glyphicon-user"> </span>
                        个人主页
                    </a>

                    <div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle navbar-btn"
                                data-toggle="dropdown">
                            <span class="caret"></span>
                                <%-- <span class="sr-only">切换下拉菜单</span>--%>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="">账户设置</a></li>
                            <li><a href="/record/list">使用记录</a></li>
                            <li><a href="/user/logout">退出</a></li>
                        </ul>
                    </div>
                    &nbsp;&nbsp;

                </c:when>
                <c:otherwise>

                    <a href="/user/login" class="navbar-link">
                        <span class="glyphicon glyphicon-log-in">登录</span>
                    </a>
                    <a href="/user/register" class="navbar-link">
                        <span class="glyphicon glyphicon-registration-mark">注册&nbsp;</span>
                    </a>

                </c:otherwise>
            </c:choose>

            <%--搜索框--%>

            <input id="kw" type="text" placeholder="请输入关键字">
            <button onclick="search()" class="btn btn-default navbar-btn">
                <span class="glyphicon glyphicon-search"></span>
            </button>
        </div>
    </div>

</nav>
<%--<div style="height: 50px;width:100%;"></div>--%>
<%--<h2 class="page-header text-center">欢迎来到大数据算法库平台</h2>--%>
<div class="container-fluid row" style="margin-top: 1px;padding-left: 1px;">

    <div class="col-md-2" style="padding-bottom: 1px;">
        <ul class="nav navbar-inverse" <%--style="background: #afd9ee;"--%>>
            <li class="active"><a href="/run/start" class="navbar-link">启动Hadoop</a></li>
            <li><a href="/run/stop" class="navbar-link">关闭Hadoop</a></li>
            <li><a href="/io/upload" class="navbar-link">上传jar包</a></li>
            <li><a href="/record/list" class="navbar-link">显示使用记录</a></li>
            <%--<li><a href="" </li>--%>
            <li><a href="/run/shell" class="navbar-link">远程终端</a></li>
            <li>...</li>
            <li>...</li>
            <li>...</li>
            <li>...</li>
        </ul>
    </div>
    <div class="col-md-8">
        <h3 class="page-header text-center">去${url}的路上出了点事故</h3>
        <p class="alert alert-danger">${exceptionMessage}</p>
    </div>

</div>

</body>
</html>
